package com.jsayago77.currx.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RateOption(
    val rate: Double,
    val type: String,
    val source: String
)