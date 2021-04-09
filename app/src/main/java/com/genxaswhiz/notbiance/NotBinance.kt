package com.genxaswhiz.notbiance

import androidx.multidex.MultiDexApplication
import com.genxaswhiz.library.Library

class NotBinance : MultiDexApplication() {
    companion object {
        val BASE_URL = "https://api.coinranking.com/v2"
        val API_KEY = "" // TODO: Use your own api key here
    }

    override fun onCreate() {
        super.onCreate()

        Library.init(this, BASE_URL)
    }
}