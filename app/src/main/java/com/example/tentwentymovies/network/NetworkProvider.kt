package com.example.tentwentymovies.network

import com.example.tentwentymovies.BuildConfig
import com.example.tentwentymovies.utils.AppConstants
import com.example.tentwentymovies.utils.HttpConstants
import com.example.tentwentymovies.utils.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class NetworkProvider(var preferenceHelper: PreferencesHelper) {
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    init {
        initOkhttp()
        initRetrofit()
    }

    private fun initOkhttp() {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(HttpConstants.TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(HttpConstants.TIMEOUT, TimeUnit.MINUTES)
            .connectTimeout(HttpConstants.TIMEOUT, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(interceptor)
        }
        builder.addNetworkInterceptor(AuthenticationInterceptors())
        okHttpClient = builder.build()
    }

    private fun initRetrofit() {
        val basePath = HttpConstants.BASE_URL

        retrofit = Retrofit.Builder()
            .baseUrl(basePath)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>?): T {
        return retrofit!!.create(service)
    }

    inner class AuthenticationInterceptors : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newRequest: Request
            if (preferenceHelper != null) {
                newRequest = request.newBuilder()
                    .addHeader(
                        "Authorization",
                        AppConstants.TOKENBEARER + preferenceHelper.getAccessToken()
                    )
                    .build()
                return chain.proceed(newRequest)
            }
            return chain.proceed(request)
        }
    }

}
