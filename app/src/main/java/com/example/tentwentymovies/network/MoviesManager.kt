package com.example.tentwentymovies.network


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.tentwentymovies.common.BaseManager
import com.example.tentwentymovies.common.NetworkHelper
import com.example.tentwentymovies.database.DataBaseProvider
import com.example.tentwentymovies.database.tables.MoviesTable
import com.example.tentwentymovies.model.Movies
import com.example.tentwentymovies.response.MovieResponse
import com.example.tentwentymovies.response.moviedetail.MovieDetailResponse
import com.example.tentwentymovies.response.video.MovieVideoResponse
import com.example.tentwentymovies.utils.MoviesApiInteraction
import com.example.tentwentymovies.utils.PreferencesHelper
import retrofit2.Response


class MoviesManager(
    networkHelper: NetworkHelper,
    preferencesHelper: PreferencesHelper,
    val db: DataBaseProvider
) :
    BaseManager(networkHelper),
    MoviesApiInteraction {

    private val moviesApiInteraction: MoviesApiInteraction

    init {
        moviesApiInteraction = MoviesApiProvider(preferencesHelper)
    }

    override suspend fun getUpcomingMovies(): Response<MovieResponse> {
        return moviesApiInteraction.getUpcomingMovies()
    }

    override suspend fun getMovieDetail(movieId: String): Response<MovieDetailResponse> {
        return moviesApiInteraction.getMovieDetail(movieId)
    }

    override suspend fun getMovieVideoDetail(id: String): Response<MovieVideoResponse> {
        return moviesApiInteraction.getMovieVideoDetail(id)
    }

    suspend fun insert(movieList: MutableList<Movies>) {
        for (movie in movieList) {
            db.getMoviesDao().upsert(
                MoviesTable(
                    movie.id,
                    movie.title,
                    movie.image,
                    movie.release_date,
                    movie.adult,
                    movie.video
                )
            )
        }
    }

    fun getAllMovies(): LiveData<List<MoviesTable>> {
       return db.getMoviesDao().getAllMovies()
    }
}