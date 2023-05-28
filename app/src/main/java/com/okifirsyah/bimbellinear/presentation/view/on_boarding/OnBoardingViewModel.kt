package com.okifirsyah.bimbellinear.presentation.view.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val pref: AppPreferences) : ViewModel() {
    fun saveFirstLaunch(isFirstLaunch: Boolean) {
        viewModelScope.launch {
            pref.saveFirstLaunch(isFirstLaunch)
        }
    }
}