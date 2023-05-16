package com.okifirsyah.bimbellinear.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences

class SplashViewModel(private val pref: AppPreferences) : ViewModel() {
    fun getFirstLaunch(): LiveData<Boolean> {
        return pref.getFirstLaunch().asLiveData()
    }
}