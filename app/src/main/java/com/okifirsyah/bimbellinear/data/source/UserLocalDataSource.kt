package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.local.dao.UserDao
import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class UserLocalDataSource(private val userDao: UserDao) {
    suspend fun fetchLocalUser(): Flow<ApiResponse<BaseResponse<UserModel>>> {
        return flow {
            try {
                val user = userDao.getUser()
                emit(ApiResponse.Success(BaseResponse(503, "Offline Mode", user)))
            } catch (e: Exception) {
                Timber.tag("UserLocalDataSource").e(e.message.toString())
            }
        }
    }
}