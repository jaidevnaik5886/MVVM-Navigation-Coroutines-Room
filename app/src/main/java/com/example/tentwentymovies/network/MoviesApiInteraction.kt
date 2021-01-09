package com.example.tentwentymovies.utils

import com.example.tentwentymovies.response.MovieResponse
import com.example.tentwentymovies.response.moviedetail.MovieDetailResponse
import com.example.tentwentymovies.response.video.MovieVideoResponse
import retrofit2.Response


interface MoviesApiInteraction {
   suspend fun getUpcomingMovies(): Response<MovieResponse>
   suspend fun getMovieDetail(movieId: String): Response<MovieDetailResponse>
    suspend fun getMovieVideoDetail(id: String): Response<MovieVideoResponse>


}