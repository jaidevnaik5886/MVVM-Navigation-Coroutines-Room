package com.example.tentwentymovies.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager

class Utilities {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }

        fun isNotEmpty(responseObject: Any?): Boolean {
            return responseObject != null
        }

        fun hideKeyboard(activity: Activity) {
            try {
                if (activity.currentFocus != null) {
                    val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    methodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
                }
            } catch (e: Exception) {
            }
        }

    }

}