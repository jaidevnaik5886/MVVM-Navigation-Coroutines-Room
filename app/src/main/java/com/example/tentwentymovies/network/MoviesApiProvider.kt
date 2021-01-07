package com.example.tentwentymovies.network

import com.example.tentwentymovies.utils.HttpConstants
import com.example.tentwentymovies.utils.PreferencesHelper
import com.example.tentwentymovies.utils.MoviesApiInteraction
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class MoviesApiProvider(preferencesHelper: PreferencesHelper) : NetworkProvider(preferencesHelper),
    MoviesApiInteraction {

    private val moviesAPIService: MoviesAPIService

    init {
        moviesAPIService = create(MoviesAPIService::class.java)
    }



    private interface MoviesAPIService {

//        @Headers("Content-Type: application/json")
//        @GET(HttpConstants.API_GET_MOVIES)
//      suspend fun getMovies(@Query("q") keyword: String): Response<>

    }
}