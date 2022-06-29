package com.genxaswhiz.library.extension

import android.util.DisplayMetrics

fun Int.convertDPtoPX(displayMetrics: DisplayMetrics) = Math.round(this * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))