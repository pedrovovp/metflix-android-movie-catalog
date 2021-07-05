package com.metflix.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor : Interceptor {
    companion object {
        const val AUTHORIZATION = "Authorization"
        const val TOKEN = "a00a9dc308fe9d454be76235f4a75449"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().addHeader(AUTHORIZATION, TOKEN).build().also {
            return chain.proceed(it)
        }
    }
}