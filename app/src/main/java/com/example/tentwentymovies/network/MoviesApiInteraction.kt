package com.example.tentwentymovies.utils

import com.example.tentwentymovies.response.MovieResponse
import com.example.tentwentymovies.response.moviedetail.MovieDetailResponse
import retrofit2.Response


interface MoviesApiInteraction {
   suspend fun getUpcomingMovies(): Response<MovieResponse>
   suspend fun getMovieDetail(movieId: String): Response<MovieDetailResponse>


}