package com.genxaswhiz.library.data.repository.impl

import androidx.lifecycle.LiveData
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.entity.CoinResponse
import com.genxaswhiz.library.data.entity.DATASOURCE
import com.genxaswhiz.library.data.entity.DataWrapper
import com.genxaswhiz.library.data.remote.impl.CoinApiImpl
import com.genxaswhiz.library.data.repository.CoinRepository
import com.genxaswhiz.library.extension.toObject
import java.util.*

class CoinRepositoryImpl(private val remoteSource: CoinApiImpl) : CoinRepository {
    override fun getCoins(): LiveData<DataWrapper<List<Coin>>> {
        return object : LiveData<DataWrapper<List<Coin>>>() {
            init {
                remoteSource.getCoins().subscribe({ apiResponse ->
                    val coinResponse = apiResponse.data.toObject<CoinResponse>()
                    coinResponse?.let {
                        val coins = coinResponse.coinDataResponse.coins
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