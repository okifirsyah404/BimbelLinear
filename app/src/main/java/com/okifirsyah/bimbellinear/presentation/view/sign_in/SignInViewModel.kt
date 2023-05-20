package com.okifirsyah.bimbellinear.presentation.view.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import com.okifirsyah.bimbellinear.data.repository.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SignInViewModel(
    private val userRepository: UserRepository,
    private val pref: AppPreferences
) : ViewModel() {

    fun loginUser(
        email: String,
        password: String
    ): MutableLiveData<ApiResponse<BaseResponse<SignInResponse>>> {

        val result = MutableLiveData<ApiResponse<BaseResponse<SignInResponse>>>()
        viewModelScope.launch {

            Timber.tag("CALL_VIEW_MODEL").d("SignInViewModel")

            userRepository.login(email, password).collect {
                result.postValue(it)
            }
        }

        return result
    }

    fun saveUserToken(token: String) {
        viewModelScope.launch {
            pref.saveAuthToken(token)
        }
    }

}