package com.genxaswhiz.library.data.remote

import com.genxaswhiz.library.data.entity.ApiResponse
import io.reactivex.Single

interface NewsApi {
    fun getNews(section: String, itemNumber: Int): Single<ApiResponse>
}