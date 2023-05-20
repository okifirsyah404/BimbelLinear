package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import com.okifirsyah.bimbellinear.data.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(private val dataSource: UserDataSource) {
    suspend fun login(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<SignInResponse>>> {
        return dataSource.login(email, password).flowOn(Dispatchers.IO)
    }

    suspend fun fetchProfile(): Flow<ApiResponse<BaseResponse<UserModel>>> {
        return dataSource.fetchUserProfile().flowOn(Dispatchers.IO)
    }
}