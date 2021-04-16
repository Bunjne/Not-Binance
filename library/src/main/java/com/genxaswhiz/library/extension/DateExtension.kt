package com.genxaswhiz.library.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(
    dateFormat: String = "E, dd MMM yyyy HH:mm:ss Z",
    timeZone: TimeZone = TimeZone.getTimeZone("GMT")
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.US)
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date?.toTransactionDateFormat(): String =
    SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(this)