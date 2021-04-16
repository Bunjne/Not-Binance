package com.genxaswhiz.library.data.remote.impl

import android.util.Log
import com.genxaswhiz.library.BuildConfig
import com.genxaswhiz.library.Library
import com.genxaswhiz.library.data.entity.ApiResponse
import com.genxaswhiz.library.data.remote.NewsApi
import com.genxaswhiz.library.extension.fetch
import com.genxaswhiz.library.utility.transformApiResponse
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import io.reactivex.Single

class NewsApiImpl : NewsApi {
    override fun getNews(section: String, itemNumber: Int): Single<ApiResponse> {
        return Single.create<ApiResponse> {
            Log.i("Check", "1")
            "https://cryptonews-api.com/api/v1/category"
                .httpGet(
                    listOf(
                        "section" to section,
                        "items" to itemNumber,
                        "token" to BuildConfig.NEWS_ACCESS_KEY
                    )
                )
                .fetch { request, response, result ->
                    val apiResponse = transformApiResponse(response, result)
                    it.onSuccess(apiResponse)
                }
        }
    }
}