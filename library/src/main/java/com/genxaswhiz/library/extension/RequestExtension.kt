package com.genxaswhiz.library.extension

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result

fun Request.fetch(callback: (Request, Response, Result<String, FuelError>) -> Unit) {
    this.timeout(6000)
    this.timeoutRead(6000)
    this.responseString { req, res, result ->
        callback(req, res, result)
    }
}