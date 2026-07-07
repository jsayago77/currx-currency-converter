package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrenciesResponse(
    val isoCode: String,
    val isoNumeric: String,
    val name: String,
    val symbol: String,
    val startDate: String,
    val endDate: String
)
