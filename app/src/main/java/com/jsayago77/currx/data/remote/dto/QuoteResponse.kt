package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponse(
    val moneda: String,
    val nombre: String,
    val compra: Double? = null,
    val venta: Double? = null,
    val ultimoCierre: Double? = null,   // Chile
    val casa: String? = null,           // Argentina
    val fuente: String? = null,         // Venezuela
    val promedio: Double? = null,       // Venezuela
    val fechaActualizacion: String
)
