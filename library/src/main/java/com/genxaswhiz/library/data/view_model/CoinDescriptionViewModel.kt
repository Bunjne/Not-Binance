package com.genxaswhiz.library.data.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genxaswhiz.library.data.entity.ApiErrorResponse
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.repository.impl.CoinRepositoryImpl

class CoinDescriptionViewModel(private val coinRepository: CoinRepositoryImpl) : ViewModel() {
    val state = State(
        coinLoading = MutableLiveData()
    )

    val coin = MutableLiveData<Coin>()
    val coinErrorResponse = MutableLiveData<ApiErrorResponse>()

    fun getCoinDescription(id: String) {
        state.coinLoading.value = true
        coinRepository.getCoinDescription(id).observeForever {
            state.coinLoading.value = false

            val data = it?.data
            data?.let {
                coin.value = it
            } ?: {
                coinErrorResponse.value = it?.error
            }()
        }
    }

    data class State(
        val coinLoading: MutableLiveData<Boolean>
    )
}