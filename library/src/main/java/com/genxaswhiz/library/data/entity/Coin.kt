package com.genxaswhiz.library.data.entity

import com.genxaswhiz.library.extension.formatNumber
import com.genxaswhiz.library.extension.setNumberFormat
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToLong

data class CoinResponse(
    @SerializedName("data") val coinDataResponse: CoinDataResponse
)

data class CoinDataResponse(
    @SerializedName("coins") val coins: List<Coin>
)

data class CoinDescriptionResponse(
    @SerializedName("data") val coinDataResponse: CoinDescriptionDataResponse
)

data class CoinDescriptionDataResponse(
    @SerializedName("coin") val coin: Coin
)


data class Coin(
    @SerializedName("uuid") val id: String = "",
    @SerializedName("symbol") val symbol: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("iconUrl") val iconUrl: String = "",
    @SerializedName("price") val _price: String = "",
    @SerializedName("24hVolume") val _volume: String = "",
    @SerializedName("change") val _change: String = "",
    @SerializedName("marketCap") val _marketCap: String = "",
    @SerializedName("rank") val rank: String = "",
    @SerializedName("supply") val supply: Supply,
    @SerializedName("description") val description: String = ""
) {
    val fullCoinName: String get() = "$name $symbol"
    val change: String get() = "${_change.toDouble().setNumberFormat(2)}%"
    val price: String get() = _price.formatNumber()
    val volume: String get() = _volume.formatNumber()
    val marketCap: String get() = _marketCap.formatNumber()
    val totalSupply: String get() = "${supply.total} $symbol"
    val circulating: String get() = "${supply.circulating} $symbol"
}

data class Supply(
    @SerializedName("circulating") val _circulating: String = "",
    @SerializedName("total") val _total: String = ""
) {
    val circulating: String get() = _circulating.formatNumber()
    val total: String get() = _total.formatNumber()
}
