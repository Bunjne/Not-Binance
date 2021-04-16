package com.genxaswhiz.library.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.genxaswhiz.library.data.entity.*
import com.genxaswhiz.library.data.remote.impl.CoinApiImpl
import com.genxaswhiz.library.data.repository.CoinRepository
import com.genxaswhiz.library.extension.toObject
import com.genxaswhiz.library.widgets.main.coin.CoinPagingSource
import kotlinx.coroutines.flow.Flow
import java.util.*

class CoinRepositoryImpl(private val remoteSource: CoinApiImpl) : CoinRepository {
    override fun getCoins(sortType: String): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 50,
                maxSize = 200,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoinPagingSource(remoteSource, sortType) }
        ).flow
    }

    override fun getCoinDescription(id: String): LiveData<DataWrapper<Coin>> {
        return object : LiveData<DataWrapper<Coin>>() {
            init {
                remoteSource.getCoinDescription(id).subscribe({ apiResponse ->
                    val coinResponse = apiResponse.data.toObject<CoinDescriptionResponse>()
                    coinResponse?.let {
                        val coins = coinResponse.coinDataResponse.coin
                        value = DataWrapper(
                            data = coins,
                            error = apiResponse.error,
                            latestDateTime = Date(),
                            isNetworkPreferred = true,
                            dataSource = DATASOURCE.NETWORK
                        )
                    }
                }, { })
            }
        }
    }
}