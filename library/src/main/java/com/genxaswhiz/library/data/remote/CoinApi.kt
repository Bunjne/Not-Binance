package com.genxaswhiz.library.data.remote

import com.genxaswhiz.library.data.entity.ApiResponse
import io.reactivex.Single

interface CoinApi {
    fun getCoins(): Single<ApiResponse>
}