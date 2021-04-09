package com.genxaswhiz.library.data.remote.impl

import com.genxaswhiz.library.data.entity.ApiResponse
import com.genxaswhiz.library.data.remote.CoinApi
import com.genxaswhiz.library.extension.fetch
import com.genxaswhiz.library.utility.transformApiResponse
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import io.reactivex.Single

class CoinApiImpl : CoinApi {
    override fun getCoins(): Single<ApiResponse> {
        return Single.create<ApiResponse> {
            "/coins"
                .httpGet()
                .fetch { request, response, result ->
                    val apiResponse = transformApiResponse(response, result)
                    it.onSuccess(apiResponse)
                }
        }
    }
}