package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DollarRateResponse(
    val moneda: String,
    val nombre: String,
    val compra: Double? = null,
    val venta: Double? = null,
    val promedio: Double? = null,   // Venezuela
    val fix: Double? = null,        // México
    val casa: String? = null,       // Argentina, Bolivia
    val fuente: String? = null,     // Venezuela
    val fechaActualizacion: String
)
