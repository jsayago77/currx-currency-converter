package com.jsayago77.currx.data.remote.interceptor

import com.jsayago77.currx.utils.countryDollar
import okhttp3.Interceptor
import okhttp3.Response

class CountryInterceptor : Interceptor {
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