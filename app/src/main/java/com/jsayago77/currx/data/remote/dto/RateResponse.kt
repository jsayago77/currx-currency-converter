package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RateResponse(
    val date: String,
    val base: String,
    val quote: String,
    val rate: Double

)