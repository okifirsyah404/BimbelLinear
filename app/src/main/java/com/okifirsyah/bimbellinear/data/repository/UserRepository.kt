package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import com.okifirsyah.bimbellinear.data.network.response.SupportContactResponse
import com.okifirsyah.bimbellinear.data.source.UserDataSource
import com.okifirsyah.bimbellinear.data.source.UserLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(
    private val remoteDataSource: UserDataSource,
    private val localDataSource: UserLocalDataSource
) {
    suspend fun login(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<SignInResponse>>> {
        return remoteDataSource.login(email, password).flowOn(Dispatchers.IO)
    }

    suspend fun fetchProfile(): Flow<ApiResponse<BaseResponse<UserModel>>> {
        return remoteDataSource.fetchUserProfile().flowOn(Dispatchers.IO)
    }

    suspend fun fetchLocalProfile(): Flow<ApiResponse<BaseResponse<UserModel>>> {
        return localDataSource.fetchLocalUser().flowOn(Dispatchers.IO)
    }

    suspend fun getOtpChangePassword(): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.getOtpChangePassword().flowOn(Dispatchers.IO)
    }

    suspend fun sendOtpChangePassword(otp: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.sendOtpChangePassword(otp).flowOn(Dispatchers.IO)
    }

    suspend fun changePassword(password: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.sendChangePassword(password).flowOn(Dispatchers.IO)
    }

    suspend fun getSupportContact(): Flow<ApiResponse<BaseResponse<SupportContactResponse>>> {
        return remoteDataSource.getSupportContact().flowOn(Dispatchers.IO)
    }

    suspend fun logout(): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.logout().flowOn(Dispatchers.IO)
    }

    suspend fun getOtpResetPassword(email: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.getOtpResetPassword(email).flowOn(Dispatchers.IO)
    }

    suspend fun sendOtpResetPassword(otp: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.sendOtpResetPassword(otp).flowOn(Dispatchers.IO)
    }

    suspend fun resetPassword(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return remoteDataSource.sendResetPassword(email, password).flowOn(Dispatchers.IO)
    }


}