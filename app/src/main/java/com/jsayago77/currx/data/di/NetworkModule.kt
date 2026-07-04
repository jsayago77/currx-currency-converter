package com.jsayago77.currx.data.di

import com.jsayago77.currx.data.remote.api.ExchangeRateApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    private const val BASE_URL = "dolarapi.com/"

    private val country = listOf("", "cl", "ve", "uy", "mx", "bo", "br", "co")

    // Create the logging interceptor
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY //Log level
    }

    // Build the OkHttpClient with the interceptor
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val api: ExchangeRateApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://$BASE_URL")
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build().create(ExchangeRateApi::class.java)
    }

}