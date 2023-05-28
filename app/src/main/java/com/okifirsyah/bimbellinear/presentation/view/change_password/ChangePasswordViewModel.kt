package com.okifirsyah.bimbellinear.presentation.view.change_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.repository.UserRepository
import kotlinx.coroutines.launch

class ChangePasswordViewModel(val repository: UserRepository) : ViewModel() {

    fun changePassword(password: String): MutableLiveData<ApiResponse<BaseResponse<Nothing>>> {
        val result = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

        viewModelScope.launch {
            repository.changePassword(password).collect {
                result.postValue(it)
            }
        }

        return result
    }

    fun resetPassword(
        email: String,
        password: String
    ): MutableLiveData<ApiResponse<BaseResponse<Nothing>>> {
        val result = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

        viewModelScope.launch {
            repository.resetPassword(email, password).collect {
                result.postValue(it)
            }
        }

        return result
    }

}