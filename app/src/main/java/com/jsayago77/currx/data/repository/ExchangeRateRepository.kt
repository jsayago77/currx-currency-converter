package com.jsayago77.currx.data.repository

import com.jsayago77.currx.data.remote.api.ExchangeRateApi

class ExchangeRateRepository(
    private val api: ExchangeRateApi
) {
    suspend fun getRate(from: String, to: String): Result<Double> {
        return try {
            val response = api.getLatestDollarRates(from)
            val rate = response.rates[to]
                ?: return Result.failure(IllegalArgumentException("Moneda no soportada: $to"))
            Result.success(rate)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}