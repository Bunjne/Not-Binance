package com.genxaswhiz.library.data.entity

import java.util.*

enum class DATASOURCE {
    CACHE,
    NETWORK
}

data class DataWrapper<out T>(val data: T?,
                              val error: ApiErrorResponse?,
                              val statusCode: Int? = null,
                              val latestDateTime: Date? = null,
                              val isNetworkPreferred: Boolean? = true,
                              val dataSource: DATASOURCE)