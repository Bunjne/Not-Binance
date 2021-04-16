package com.genxaswhiz.library.data.view_model

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.repository.impl.CoinRepositoryImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class CoinViewModel(private val coinRepository: CoinRepositoryImpl) : ViewModel() {
    val state = State(
        coinLoading = MutableLiveData()
    )

    var coinResponse = MutableLiveData<PagingData<Coin>>()

    suspend fun getCoins(sortType: String, search: String) {
        state.coinLoading.value = true
        coinRepository.getCoins(sortType)
            .map { pagingData ->
                pagingData.filter { coinData ->
                    if (search.isNullOrEmpty()) {
                        true
                    } else {
                        coinData.fullCoinName.toLowerCase().contains(search)
                    }
                }
            }
            .cachedIn(viewModelScope)
            .collectLatest { coins ->
                state.coinLoading.value = false
                coinResponse.value = coins
            }

    }

    data class State(
        val coinLoading: MutableLiveData<Boolean>
    )
}