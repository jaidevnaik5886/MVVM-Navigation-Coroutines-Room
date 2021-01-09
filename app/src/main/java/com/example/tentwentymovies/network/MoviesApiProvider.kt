package com.example.tentwentymovies.network

import com.example.tentwentymovies.response.MovieResponse
import com.example.tentwentymovies.response.moviedetail.MovieDetailResponse
import com.example.tentwentymovies.response.video.MovieVideoResponse
import com.example.tentwentymovies.utils.HttpConstants
import com.example.tentwentymovies.utils.MoviesApiInteraction
import com.example.tentwentymovies.utils.PreferencesHelper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class MoviesApiProvider(preferencesHelper: PreferencesHelper) : NetworkProvider(preferencesHelper),
    MoviesApiInteraction {

    private val moviesAPIService: MoviesAPIService

    init {
        moviesAPIService = create(MoviesAPIService::class.java)
    }

    override suspend fun getUpcomingMovies(): Response<MovieResponse> {
       return moviesAPIService.getUpcomingMovies()
    }

    override suspend fun getMovieDetail(movieId: String): Response<MovieDetailResponse> {
        return moviesAPIService.getMovieDetail(movieId)
    }

    override suspend fun getMovieVideoDetail(movieId: String): Response<MovieVideoResponse> {
        return moviesAPIService.getMovieVideoDetail(movieId)
    }

    private interface MoviesAPIService {

        @Headers("Content-Type: application/json")
        @GET(HttpConstants.API_GET_MOVIES)
        suspend fun getUpcomingMovies(): Response<MovieResponse>

        @Headers("Content-Type: application/json")
        @GET(HttpConstants.API_GET_MOVIE_DETAIL)
        suspend fun getMovieDetail(@Path("movieId") movieId : String): Response<MovieDetailResponse>

        @Headers("Content-Type: application/json")
        @GET(HttpConstants.API_GET_MOVIE_VIDEO_DETAIL)
        suspend fun getMovieVideoDetail(@Path("movieId") movieId : String): Response<MovieVideoResponse>

    }
}