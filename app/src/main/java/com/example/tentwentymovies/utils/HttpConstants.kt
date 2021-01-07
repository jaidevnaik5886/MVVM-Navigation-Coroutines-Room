package com.example.tentwentymovies.utils

import com.example.tentwentymovies.BuildConfig


class HttpConstants {

    companion object {
        const val TIMEOUT: Long = 2
        const val BASE_URL: String = BuildConfig.SERVER_URL_ROOT
        const val API_GET_MOVIES =""
    }

}