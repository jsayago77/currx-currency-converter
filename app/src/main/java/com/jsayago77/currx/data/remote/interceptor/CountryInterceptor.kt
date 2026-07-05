package com.jsayago77.currx.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CountryInterceptor : Interceptor {

    private val countryDollar = mapOf("arg" to "", "cl" to "cl", "ve" to "ve", "uy" to "uy", "mx" to "mx", "bo" to "bo", "br" to "br", "co" to "co")

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val country = original.header("X-Country") ?: ""
        val countryCode = countryDollar[country] ?: return chain.proceed(original)
        val subdomain = if (countryCode.isEmpty()) "" else "$countryCode."
        val newUrl = original.url.newBuilder()
            .host("${subdomain}dolarapi.com")
            .build()
        val newRequest = original.newBuilder()
            .url(newUrl)
            .removeHeader("X-Country")
            .build()
        return chain.proceed(newRequest)

    }
}