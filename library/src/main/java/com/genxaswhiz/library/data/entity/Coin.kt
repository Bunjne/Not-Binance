package com.genxaswhiz.library.data.entity

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("data") val coinDataResponse: CoinDataResponse
)

data class  CoinDataResponse(
    @SerializedName("coins") val coins: List<Coin>
)

data class Coin(
    @SerializedName("uuid") val id: String = "",
    @SerializedName("symbol") val symbol: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("iconUrl") val iconUrl: String = "",
    @SerializedName("price") val price: String = "",
    @SerializedName("24hVolume") val volume: String = ""
)