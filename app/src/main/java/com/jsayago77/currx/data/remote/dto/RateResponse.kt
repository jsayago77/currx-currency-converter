package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RateResponse(
    val base: String,
    val rates: List<DollarRate>,
    val date: String
)
