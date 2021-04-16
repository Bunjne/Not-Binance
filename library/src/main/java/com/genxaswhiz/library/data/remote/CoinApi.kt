package com.genxaswhiz.library.data.remote

import com.genxaswhiz.library.data.entity.ApiResponse
import com.github.kittinunf.fuel.core.Response
import io.reactivex.Single

interface CoinApi {
    fun getCoins(limit: Int, offset: Int, sortType: String): Single<ApiResponse>
    fun getCoinDescription(id: String): Single<ApiResponse>
}