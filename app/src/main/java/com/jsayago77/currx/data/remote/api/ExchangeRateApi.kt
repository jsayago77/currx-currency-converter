package com.jsayago77.currx.data.remote.api

import com.jsayago77.currx.data.remote.dto.CurrenciesResponse
import com.jsayago77.currx.data.remote.dto.DollarRateResponse
import com.jsayago77.currx.data.remote.dto.LatamRateResponse
import com.jsayago77.currx.data.remote.dto.RateBrResponse
import com.jsayago77.currx.data.remote.dto.RateResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ExchangeRateApi {

    // BASE URL: dolarapi.com FOR ALL EXCEPT BOB
    @GET("/v1/cotizaciones")
    suspend fun getLatamRates(
        @Header("X-Country") country: String = ""
    ): List<LatamRateResponse>

    // BASE URL: dolarapi.com ONLY FOR BR
    @GET("/v1/cotacoes")
    suspend fun getRatesBr(
        @Header("X-Country") country: String = ""
    ): List<RateBrResponse>

    // BASE URL: dolarapi.com ONLY FOR ARS, BOB
    @GET("/v1/dolares")
    suspend fun getLatamDollarRates(
        @Header("X-Country") country: String = ""
    ): List<DollarRateResponse>

    @GET("/v2/rate/{from}/{to}")
    suspend fun getRates(
        @Path("from") from: String,
        @Path("to") to: String
    ): RateResponse

    @GET("/v2/currencies")
    suspend fun getCurrencies(): List<CurrenciesResponse>
}