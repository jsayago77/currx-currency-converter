package com.jsayago77.currx.data.repository

import com.jsayago77.currx.data.remote.api.ExchangeRateApi
import com.jsayago77.currx.data.remote.dto.CurrenciesResponse
import com.jsayago77.currx.data.remote.dto.DollarRate
import com.jsayago77.currx.data.remote.dto.RateResponse
import com.jsayago77.currx.utils.countryDollar

class ExchangeRateRepository(
    private val api: ExchangeRateApi
) {

    suspend fun getCurrencies(): Result<List<CurrenciesResponse>> {
        return try {
            val response = api.getCurrencies()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getDollarRate(country: String): Result<Any> {
        return try {
            val response = if(countryDollar.contains(country))
                api.getLatestDollarRates(country)[0].promedio
            else
                api.getRates(country, "USD").rate
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}