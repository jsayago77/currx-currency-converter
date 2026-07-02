package com.jsayago77.currx.data.remote.api

import com.jsayago77.currx.data.remote.dto.RateResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {

    @GET("/v1/dolares")
    suspend fun getLatestDollarRates(
        @Path("base") base: String
    ): RateResponse
}