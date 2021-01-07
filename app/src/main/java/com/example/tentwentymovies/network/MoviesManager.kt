package com.example.tentwentymovies.network


import com.example.tentwentymovies.common.BaseManager
import com.example.tentwentymovies.common.NetworkHelper
import com.example.tentwentymovies.utils.MoviesApiInteraction
import com.example.tentwentymovies.utils.PreferencesHelper


class MoviesManager(networkHelper: NetworkHelper, preferencesHelper: PreferencesHelper) : BaseManager(networkHelper),
    MoviesApiInteraction {

    private val moviesApiInteraction: MoviesApiInteraction

    init {
        moviesApiInteraction = MoviesApiProvider(preferencesHelper)
    }


}