package com.genxaswhiz.library.data.remote.impl

import com.genxaswhiz.library.data.entity.ApiResponse
import com.genxaswhiz.library.data.remote.CoinApi
import com.genxaswhiz.library.extension.fetch
import com.genxaswhiz.library.utility.transformApiResponse
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import io.reactivex.Single

class CoinApiImpl : CoinApi {
    override fun getCoins(limit: Int, offset: Int, sortType: String): Single<ApiResponse> {
        return Single.create<ApiResponse> {
            "/coins"
                .httpGet(
                    listOf(
                        "limit" to limit,
                        "offset" to offset,
                        "orderDirection" to sortType
                    )
                )
                .fetch { request, response, result ->
                    val apiResponse = transformApiResponse(response, result)
                    it.onSuccess(apiResponse)
                }
        }
    }

    override fun getCoinDescription(id: String): Single<ApiResponse> {
        return Single.create<ApiResponse> {
            "/coin/$id"
                .httpGet()
                .fetch { request, response, result ->
                    val apiResponse = transformApiResponse(response, result)
                    it.onSuccess(apiResponse)
                }
        }
    }
}