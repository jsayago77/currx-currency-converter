package com.jsayago77.currx.data.repository

import com.jsayago77.currx.data.remote.api.ExchangeRateApi
import com.jsayago77.currx.data.remote.dto.CurrenciesResponse
import com.jsayago77.currx.data.remote.dto.RateOption
import com.jsayago77.currx.data.remote.mapper.toRateOption
import com.jsayago77.currx.utils.countryLatam

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

    suspend fun getRate(from: String, to: String): Result<List<RateOption>> {
        return try {
            val resp = when {
               from in countryLatam -> getLatamRates(from, to)
               to in countryLatam -> invertLatamRates(to, from)
               else -> listOf(getRateNormal(from, to))
            }
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun getLatamRates(from: String, to: String): List<RateOption> {
        return when(from) {
            "BRL" -> {
                api.getCotizacionesBR(from).filter { it.moneda == to }.map { it.toRateOption() }
            }
            "ARS, BOB" -> {
                if(to == "USD") api.getLatestDollarRates(from).map { it.toRateOption() }
                else listOf(api.getRates(from, to)).map { it.toRateOption() }
            }
            else -> {
                api.getCotizaciones(from).filter { it.moneda == to }.map { it.toRateOption() }
            }
        }
    }

    private suspend fun getRateNormal(from: String, to: String): RateOption {
            return api.getRates(from, to).toRateOption()
    }

    private suspend fun invertLatamRates(
        latamCurrency: String, foreignCurrency: String
    ): List<RateOption> {
        val rates = getLatamRates(latamCurrency, foreignCurrency)

        return rates.map { option ->
            option.copy(rate = 1.0 / option.rate)
        }
    }
}