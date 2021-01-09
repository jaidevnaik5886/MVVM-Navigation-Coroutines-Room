package com.example.tentwentymovies.ui.movielisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tentwentymovies.network.MoviesManager

class MoviesViewModelProviderFactory(
    val moviesManager: MoviesManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListingViewModel(moviesManager) as T
    }
}