package com.example.tentwentymovies.common

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tentwentymovies.R
import com.example.tentwentymovies.utils.PreferencesHelper
import com.example.tentwentymovies.utils.Utilities


open class BaseActivity : AppCompatActivity(), BaseView, NetworkHelper {

    fun getBaseApp(): BaseApplication? {
        return application as BaseApplication
    }

    override fun isNetworkAvailable(): Boolean {
        return Utilities.isNetworkAvailable(this)
    }

    override fun getNoInternetMessage(): String? {
        return getString(R.string.no_internet_connection)
    }

    override fun getNetworkHelper(): NetworkHelper {
        return this
    }

    override fun hasInternet(): Boolean? {
        return isNetworkAvailable()
    }

    override fun getPreferences(): PreferencesHelper {
        return getBaseApp()!!.getPreferences()!!
    }

    override fun showNoInternetMsg() {
        Toast.makeText(this, getNoInternetMessage(), Toast.LENGTH_SHORT).show()
    }
}
