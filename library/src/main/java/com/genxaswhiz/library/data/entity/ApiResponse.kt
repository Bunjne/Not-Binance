package com.genxaswhiz.library.data.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("data") val _data: Any?,
    @SerializedName("error") val error: ApiErrorResponse?
) {
    val data get() = _data?.toString() ?: ""
}
