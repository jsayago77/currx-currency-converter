package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DollarRate(
    val moneda: String,
    val fuente: String,
    val nombre: String,
    val compra: Double? = null,
    val venta: Double? = null,
    val promedio: Double,
    val fechaActualizacion: String
)

