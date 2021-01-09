package com.example.tentwentymovies.common

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>() : ViewModel() {
    private var mNavigator: WeakReference<N?>? = null

    var navigator: N?
        get() = mNavigator!!.get()
        set(navigator) {
            mNavigator = WeakReference(navigator)
        }

}