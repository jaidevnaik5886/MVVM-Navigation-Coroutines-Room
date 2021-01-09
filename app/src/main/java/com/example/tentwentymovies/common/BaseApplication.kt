package com.example.tentwentymovies.common

import android.app.Application
import androidx.lifecycle.LifecycleObserver


import com.example.tentwentymovies.utils.AppConstants
import com.example.tentwentymovies.utils.AppPreferencesHelper
import com.example.tentwentymovies.utils.PreferencesHelper
import com.example.tentwentymovies.utils.Utilities


class BaseApplication : Application(), LifecycleObserver {

    private var preferencesHelper: PreferencesHelper? = null

    override fun onCreate() {
        super.onCreate()
        initAppModules()
    }

    private fun initAppModules() {
        preferencesHelper = AppPreferencesHelper(this, AppConstants.PREF_FILE_NAME)
    }

    fun getPreferences(): PreferencesHelper? {
        return preferencesHelper
    }


}
