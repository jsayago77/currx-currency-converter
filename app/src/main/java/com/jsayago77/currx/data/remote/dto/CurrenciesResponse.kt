package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrenciesResponse(
    @SerialName("iso_code") val isoCode: String,
    @SerialName("iso_numeric") val isoNumeric: String,
    val name: String,
    val symbol: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String
)
