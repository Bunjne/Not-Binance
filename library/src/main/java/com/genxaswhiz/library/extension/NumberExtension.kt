package com.genxaswhiz.library.extension

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToLong

fun String.formatNumber(): String {
    return if (this.isNullOrEmpty()) {
        "N/A"
    } else {
        truncateNumber(this.toDouble())
    }
}

fun Double.setNumberFormat(scale: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    var decimal = BigDecimal(this).setScale(scale, RoundingMode.HALF_EVEN)
    return formatter.format(decimal)
}

private fun truncateNumber(number: Double): String {
    val m = 1000000L
    val b = 1000000000L
    val t = 1000000000000L
    val roundedNumber = number.roundToLong()

    return when {
        (roundedNumber >= m) and (number < b) -> {
            calculateFraction(roundedNumber, m) + " M"
        }
        (roundedNumber >= b) and (roundedNumber < t) -> {
            calculateFraction(roundedNumber, b) + " Bn"
        }
        roundedNumber >= t -> {
            calculateFraction(roundedNumber, t) + " T"
        }
        else -> number.setNumberFormat(3)
    }
}

private fun calculateFraction(number: Long, divisor: Long): String {
    val truncate = (number * 1000L + (divisor / 2L)) / divisor
    val result = (truncate * 0.001F).toDouble()

    if (result < 10) return result.setNumberFormat(3)
    return result.setNumberFormat(2)
}