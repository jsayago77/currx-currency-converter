package com.jsayago77.currx.data.di

import com.jsayago77.currx.data.remote.api.ExchangeRateApi
import com.jsayago77.currx.data.remote.interceptor.CountryInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://api.frankfurter.dev"

    // Create the logging interceptor
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY //Log level
    }

    val countryInterceptor = CountryInterceptor()

    // Build the OkHttpClient with the interceptor
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(countryInterceptor)
        .build()

    val networkJson = Json

    val api: ExchangeRateApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build().create(ExchangeRateApi::class.java)
    }

}