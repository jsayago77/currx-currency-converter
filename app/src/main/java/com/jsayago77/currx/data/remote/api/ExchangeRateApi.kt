package com.jsayago77.currx.data.remote.api

import com.jsayago77.currx.data.remote.dto.CurrenciesResponse
import com.jsayago77.currx.data.remote.dto.DollarRateResponse
import com.jsayago77.currx.data.remote.dto.QuoteResponse
import com.jsayago77.currx.data.remote.dto.QuoteBRResponse
import com.jsayago77.currx.data.remote.dto.RateResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ExchangeRateApi {

    // BASE URL: dolarapi.com FOR ALL EXCEPT BOB
    @GET("/v1/cotizaciones")
    suspend fun getCotizaciones(
        @Header("X-Country") country: String = ""
    ): List<QuoteResponse>

    // BASE URL: dolarapi.com ONLY FOR BR
    @GET("/v1/cotacoes")
    suspend fun getCotizacionesBR(
        @Header("X-Country") country: String = ""
    ): List<QuoteBRResponse>

    // BASE URL: dolarapi.com ONLY FOR ARS, BOB
    @GET("/v1/dolares")
    suspend fun getLatestDollarRates(
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