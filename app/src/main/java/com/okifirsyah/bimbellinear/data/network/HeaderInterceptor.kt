package com.okifirsyah.bimbellinear.data.network

import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject

class HeaderInterceptor(private val requestHeaders: HashMap<String, String>) : Interceptor {

    private val appPreferences: AppPreferences by inject(AppPreferences::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url.toString()
        print("<<<<<<<<< $path")
        if (path.contains("logout")) {
            mapRequestHeaders()
            print("<<<<<<<<< $requestHeaders")
        } else {
            mapRequestHeaders()
        }

        val request = mapHeaders(chain)

        return chain.proceed(request)

    }

    private fun mapRequestHeaders() {
        println("<<<<<<<<< Before : $requestHeaders")

        runBlocking {
            val token = appPreferences.getAuthToken().first()

            println(token)

            requestHeaders["Authorization"] = token
        }

        println("<<<<<<<<< After $requestHeaders")
    }


    private fun mapHeaders(chain: Interceptor.Chain): Request {
        val original = chain.request()

        val requestBuilder = original.newBuilder()

        for ((key, value) in requestHeaders) {
            requestBuilder.addHeader(key, value)
        }
        return requestBuilder.build()
    }
}