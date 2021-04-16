package com.genxaswhiz.library.widgets.main.coin

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.bumptech.glide.load.HttpException
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.entity.CoinResponse
import com.genxaswhiz.library.data.remote.impl.CoinApiImpl
import com.genxaswhiz.library.extension.toObject
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException


class CoinPagingSource(
    private val coinApi: CoinApiImpl,
    private val sortType: String) : RxPagingSource<Int, Coin>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Coin>> {
        val offset = params.key ?: STARTING_PAGE_INDEX
        val loadSize = PAGE_SIZE
        return coinApi.getCoins(loadSize, offset, sortType)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, Coin>> { result ->
                val coinResponse = result.data.toObject<CoinResponse>()
                coinResponse?.let {
                    val coins = it.coinDataResponse.coins
                    LoadResult.Page(
                        data = coins,
                        prevKey = if (offset == STARTING_PAGE_INDEX) null else offset - PAGE_SIZE,
                        nextKey = if (coins.isNullOrEmpty() || offset >= MAX_SIZE - 10) null else offset + PAGE_SIZE
                    )
                }
            }
            .onErrorReturn { error ->
                when (error) {
                    is IOException -> LoadResult.Error(error)
                    is HttpException -> LoadResult.Error(error)
                    else -> LoadResult.Error(error)
                }
            }
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
        private const val PAGE_SIZE = 10
        private const val MAX_SIZE = 200
    }

}