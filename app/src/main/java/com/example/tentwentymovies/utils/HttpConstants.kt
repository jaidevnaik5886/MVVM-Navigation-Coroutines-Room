package com.example.tentwentymovies.utils

import com.example.tentwentymovies.BuildConfig


class HttpConstants {

    companion object {
        const val TIMEOUT: Long = 2
        const val TOKEN ="46b98682f0d5a7f4d9fc65a08d8eccd4"
        const val BASE_URL: String = BuildConfig.SERVER_URL_ROOT
        const val API_GET_MOVIES ="3/movie/upcoming?api_key=" + TOKEN
        const val API_GET_MOVIE_DETAIL ="3/movie/{movieId}?api_key="+ TOKEN
        const val IMAGE_BASE_PATH ="https://image.tmdb.org/t/p/w185/"
    }

}