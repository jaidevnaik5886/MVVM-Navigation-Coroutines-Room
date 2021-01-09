package com.example.tentwentymovies.ui.movielisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tentwentymovies.database.tables.MoviesTable
import com.example.tentwentymovies.model.MovieDetail
import com.example.tentwentymovies.model.Movies
import com.example.tentwentymovies.network.MoviesManager
import com.example.tentwentymovies.response.MovieResponse
import com.example.tentwentymovies.response.moviedetail.Genre
import com.example.tentwentymovies.response.moviedetail.MovieDetailResponse
import com.example.tentwentymovies.response.video.MovieVideoResponse
import com.example.tentwentymovies.utils.Utilities
import kotlinx.coroutines.launch
import retrofit2.Response

public class MovieListingViewModel(
    val moviesManager: MoviesManager
) : ViewModel() {

    val upcomingMovies: MutableLiveData<List<Movies>> = MutableLiveData()
    val video: MutableLiveData<String> = MutableLiveData()
    val movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()


    init {
        if (moviesManager.networkHelper.hasInternet()!!) {
            getUpcomingMovies()
        }
    }

    fun getUpcomingMovies() = viewModelScope.launch {
        val response = moviesManager.getUpcomingMovies()
        upcomingMovies.postValue(processUpcomingMovies(response))
    }

    fun getMovieDetail(movieId: String) = viewModelScope.launch {
        val response = moviesManager.getMovieDetail(movieId)
        movieDetail.postValue(processMovieDetail(response))
    }

    fun getMovieVideoDetail(movieId: String) = viewModelScope.launch {
        val response = moviesManager.getMovieVideoDetail(movieId)
        video.postValue(processVideoDetails(response))
    }

    private fun processVideoDetails(response: Response<MovieVideoResponse>): String {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                 result.results.forEach{
                     return it.key
                 }
            }
        }
        return ""
    }

    private fun processMovieDetail(response: Response<MovieDetailResponse>): MovieDetail {
        val movieDetail = MovieDetail()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return MovieDetail(
                    result.backdrop_path,
                    result.title,
                    getGenres(result.genres),
                    result.release_date,
                    result.overview,
                    result.popularity.toString()
                )
            }
        }
        return movieDetail
    }

    private fun getGenres(genres: List<Genre>): String {
        val string = StringBuilder()
        for (i in genres.indices) {
            string.append(genres[i].name)
            if (i < (genres.size - 1)) {
                string.append(",")
            }
        }
        return string.toString()
    }

    fun saveMovie(movieList: MutableList<Movies>) = viewModelScope.launch {
        moviesManager.insert(movieList)
    }

    fun getAllMovies(): LiveData<List<MoviesTable>> {
        if (!moviesManager.networkHelper.hasInternet()!!){
          return  moviesManager.getAllMovies()
        }
        return MutableLiveData()
    }


    private fun processUpcomingMovies(response: Response<MovieResponse>): List<Movies> {
        val movieList = mutableListOf<Movies>()
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                for (result in resultResponse.results) {
                    val movie = Movies()
                    movie.id = result.id.toString()
                    movie.title = result.title
                    movie.image = result.poster_path
                    movie.release_date = result.release_date
                    movie.adult = result.adult
                    movie.video = result.video
                    movieList.add(movie)
                }
            }
        }
        saveMovie(movieList)
        return movieList
    }



    fun movieDetail(movieId: String) {
        if (moviesManager.networkHelper.hasInternet()!!) {
            getMovieDetail(movieId)
        }
    }

}


