package com.okifirsyah.bimbellinear.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import com.okifirsyah.bimbellinear.data.repository.UserRepository

class SplashViewModel(
    private val pref: AppPreferences,
    private val userRepository: UserRepository
) : ViewModel() {
    fun getFirstLaunch(): LiveData<Boolean> {
        return pref.getFirstLaunch().asLiveData()
    }

    fun getAuthToken(): LiveData<String> {
        return pref.getAuthToken().asLiveData()
    }
}