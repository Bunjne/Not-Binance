package com.genxaswhiz.library.data.repository

import androidx.lifecycle.LiveData
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.entity.DataWrapper

interface CoinRepository {
    fun getCoins(): LiveData<DataWrapper<List<Coin>>>
}