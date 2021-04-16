package com.genxaswhiz.library.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.entity.DataWrapper
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(sortType: String): Flow<PagingData<Coin>>
    fun getCoinDescription(id: String): LiveData<DataWrapper<Coin>>
}