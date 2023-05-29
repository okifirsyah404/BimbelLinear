package com.okifirsyah.bimbellinear.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.repository.ScheduleRepository
import com.okifirsyah.bimbellinear.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pref: AppPreferences,
    private val scheduleRepository: ScheduleRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val userProfileResult: LiveData<ApiResponse<BaseResponse<UserModel>>> by lazy { _userProfileResult }
    private val _userProfileResult =
        MutableLiveData<ApiResponse<BaseResponse<UserModel>>>()

    val userToken: MutableLiveData<String> by lazy { _userToken }
    private val _userToken = MutableLiveData<String>()

    val scheduleResult: LiveData<ApiResponse<BaseListResponse<ScheduleModel>>> by lazy { _scheduleResult }
    private val _scheduleResult =
        MutableLiveData<ApiResponse<BaseListResponse<ScheduleModel>>>()

    val changePasswordNotification: LiveData<Boolean> by lazy { _changePasswordNotification }
    private val _changePasswordNotification = MutableLiveData<Boolean>()

    fun getUserToken() {
        viewModelScope.launch {
            pref.getAuthToken().collect {
                _userToken.postValue(it)
            }
        }
    }

    fun setUserToken(token: String) {
        viewModelScope.launch {
            pref.saveAuthToken(token)
        }
    }

    fun getUserProfile() {
        viewModelScope.launch {
            userRepository.fetchProfile().collect {
                _userProfileResult.postValue(it)
            }
        }
    }

    fun getLocalProfile() {
        viewModelScope.launch {
            userRepository.fetchLocalProfile().collect {
                _userProfileResult.postValue(it)
            }
        }
    }

    fun fetchSchedules() {
        viewModelScope.launch {
            scheduleRepository.fetchSchedules().collect {
                _scheduleResult.postValue(it)
            }
        }
    }

    fun fetchLocalSchedules() {
        viewModelScope.launch {
            scheduleRepository.fetchLocalSchedules().collect {
                _scheduleResult.postValue(it)
            }
        }
    }

    fun getChangePasswordNotification() {
        viewModelScope.launch {
            pref.getChangePassword().collect {
                _changePasswordNotification.postValue(it)
            }
        }
    }

}