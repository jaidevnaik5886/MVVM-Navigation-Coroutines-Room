package com.example.tentwentymovies.ui.movielisting

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tentwentymovies.R
import com.example.tentwentymovies.common.BaseActivity
import com.example.tentwentymovies.database.DataBaseProvider
import com.example.tentwentymovies.network.MoviesManager

class MovieListingActivity : BaseActivity() {

    lateinit var viewModel: MovieListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val moviesManager = MoviesManager(getNetworkHelper(), getPreferences(),DataBaseProvider(this))
        val viewModelProviderFactory = MoviesViewModelProviderFactory(moviesManager)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MovieListingViewModel::class.java)
    }

}
