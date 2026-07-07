package com.jsayago77.currx.data.remote.api

import com.jsayago77.currx.data.remote.dto.DollarRate
import com.jsayago77.currx.data.remote.dto.RateResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ExchangeRateApi {

    @GET("/v1/dolares")
    suspend fun getLatestDollarRates(
        @Header("X-Country") country: String = ""
    ): List<DollarRate>

    @GET("/v2/rate/{from}/{to}")
    suspend fun getRates(
        @Path("from") from: String,
        @Path("to") to: String
    ): RateResponse
}