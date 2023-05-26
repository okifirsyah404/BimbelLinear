package com.okifirsyah.bimbellinear.presentation.view.otp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.repository.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class OtpViewModel(
    private val repository: UserRepository
) : ViewModel() {

    fun getOtpChangePassword(): MutableLiveData<ApiResponse<BaseResponse<Nothing>>> {
        val result = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

        viewModelScope.launch {
            repository.getOtpChangePassword().collect {
                result.postValue(it)
            }
        }

        return result
    }

    fun sendOtpChangePassword(otp: String): MutableLiveData<ApiResponse<BaseResponse<Nothing>>> {
        val result = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

        viewModelScope.launch {
            Timber.tag("OtpViewModel").d(otp)
            repository.sendOtpChangePassword(otp).collect {
                result.postValue(it)
            }
        }

        return result
    }

    fun sendOtpResetPassword(otp: String): MutableLiveData<ApiResponse<BaseResponse<Nothing>>> {
        val result = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

        viewModelScope.launch {
            Timber.tag("OtpViewModel").d(otp)
            repository.sendOtpResetPassword(otp).collect {
                result.postValue(it)
            }
        }

        return result
    }
}