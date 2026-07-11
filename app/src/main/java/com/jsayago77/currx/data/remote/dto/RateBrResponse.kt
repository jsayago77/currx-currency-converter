package com.jsayago77.currx.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Rate for Brazil dolarapi.com subodmain
@Serializable
data class RateBrResponse(
    @SerialName("moeda") val moneda: String,
    @SerialName("nome") val nombre: String,
    @SerialName("compra") val compra: Double? = null,
    @SerialName("venda") val venta: Double? = null,
    @SerialName("fechoAnterior") val ultimoCierre: Double? = null,
    @SerialName("dataAtualizacao") val fechaActualizacion: String
)