package com.genxaswhiz.library.data.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genxaswhiz.library.data.entity.ApiErrorResponse
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.data.repository.impl.NewsRepositoryImpl

class NewsViewModel(private val newsRepository: NewsRepositoryImpl) : ViewModel() {
    val state = State(
        newsLoading = MutableLiveData()
    )

    val newsResponse = MutableLiveData<List<News>>()
    val newsErrorResponse = MutableLiveData<ApiErrorResponse>()

    fun getNews(section: String, itemNumber: Int) {
        state.newsLoading.value = true
        newsRepository.getNews(section, itemNumber).observeForever {
            state.newsLoading.value = false

            val data = it?.data
            data?.let {
                newsResponse.value = it
            } ?: {
                newsErrorResponse.value = it?.error
            }()
        }
    }

    data class State(
        val newsLoading: MutableLiveData<Boolean>
    )
}