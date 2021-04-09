package com.genxaswhiz.library.utility

import com.genxaswhiz.library.data.entity.ApiResponse
import com.genxaswhiz.library.extension.toObject
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.result.Result

fun transformApiResponse(response: Response, result: Result<String, FuelError>): ApiResponse {
    return if (response.isSuccessful) {
        ApiResponse(
            status = response.statusCode.toString(),
            _data = result.component1().toString(),
            error = null
        )
    }
    else {
        ApiResponse(
            status = response.statusCode.toString(),
            _data = null,
            error = String(result.component2()?.response?.data ?: byteArrayOf()).toObject()
        )
    }
}