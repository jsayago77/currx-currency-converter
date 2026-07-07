package com.jsayago77.currx.data.repository

import com.jsayago77.currx.data.remote.api.ExchangeRateApi
import com.jsayago77.currx.data.remote.dto.DollarRate
import com.jsayago77.currx.data.remote.dto.RateResponse
import com.jsayago77.currx.utils.countryDollar

class ExchangeRateRepository(
    private val api: ExchangeRateApi
) {
    suspend fun getDollarRate(country: String): Result<List<DollarRate>> {
        return try {
            val response = if(countryDollar.contains(country))
                api.getLatestDollarRates(country)
            else
                api.getRates(country, "USD")
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}