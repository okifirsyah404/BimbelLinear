package com.okifirsyah.bimbellinear.presentation.view.reset_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.repository.UserRepository
import kotlinx.coroutines.launch

class ResetPasswordViewModel(private val repository: UserRepository) : ViewModel() {

    fun getOtpResetPassword(email: String): MutableLiveData<ApiResponse<BaseResponse<Nothing>>> {
        val result = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

        viewModelScope.launch {
            repository.getOtpResetPassword(email).collect {
                result.postValue(it)
            }
        }

        return result
    }

}