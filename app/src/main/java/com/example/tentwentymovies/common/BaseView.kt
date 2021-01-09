package com.example.tentwentymovies.common

import com.example.tentwentymovies.utils.PreferencesHelper

interface BaseView {

    fun isNetworkAvailable(): Boolean

    fun getNoInternetMessage(): String?

    fun getNetworkHelper(): NetworkHelper

    fun getPreferences(): PreferencesHelper

    fun showNoInternetMsg()

}