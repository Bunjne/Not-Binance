package com.genxaswhiz.library.data.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genxaswhiz.library.data.entity.ApiErrorResponse
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.repository.impl.CoinRepositoryImpl

class CoinViewModel (private val coinRepository: CoinRepositoryImpl) : ViewModel() {
    val coins = MutableLiveData<List<Coin>>()
    val coinsErrorResponse = MutableLiveData<ApiErrorResponse>()

    fun getCoins() {
        coinRepository.getCoins().observeForever {
            val data = it?.data
            data?.let {
                coins.value = it
            } ?: {
                coinsErrorResponse.value = it?.error
            }()
        }
    }
}