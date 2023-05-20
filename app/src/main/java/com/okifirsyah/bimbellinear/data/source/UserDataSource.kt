package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.request.LoginBody
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import com.okifirsyah.bimbellinear.data.network.services.UserService
import com.okifirsyah.bimbellinear.utils.extensions.createResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection

class UserDataSource(private val userService: UserService) {
    suspend fun login(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<SignInResponse>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.login(LoginBody(email, password))
                Timber.tag("Login").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Login").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("LoginErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("LoginErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun fetchUserProfile(): Flow<ApiResponse<BaseResponse<UserModel>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.getProfile()
                Timber.tag("Profile").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("ProfileErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("ProfileErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

}