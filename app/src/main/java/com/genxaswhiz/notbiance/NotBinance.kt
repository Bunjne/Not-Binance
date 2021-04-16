package com.genxaswhiz.notbiance

import androidx.multidex.MultiDexApplication
import com.genxaswhiz.library.Library

class NotBinance : MultiDexApplication() {
    companion object {
        val BASE_URL = "https://api.coinranking.com/v2"
        val API_KEY = "coinranking0ad0aca49a3bc5ebbdfbdbc18601f75da3fa14360b8b75d0" // TODO: Use your own api key here
    }

    override fun onCreate() {
        super.onCreate()

        Library.init(this, BASE_URL, API_KEY)
    }
}