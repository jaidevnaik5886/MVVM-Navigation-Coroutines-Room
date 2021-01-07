package com.example.tentwentymovies.utils

import android.content.Context
import android.content.SharedPreferences

class AppPreferencesHelper(context: Context, prefFileName: String?) : PreferencesHelper {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getAccessToken(): String {
        return AppConstants.API_KEY
    }

}
