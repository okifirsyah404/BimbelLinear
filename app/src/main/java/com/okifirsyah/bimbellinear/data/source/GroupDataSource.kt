package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.GroupResponse
import com.okifirsyah.bimbellinear.data.network.services.GroupService
import com.okifirsyah.bimbellinear.utils.extensions.createResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection

class GroupDataSource(private val groupService: GroupService) {
    suspend fun fetchGroup(): Flow<ApiResponse<BaseResponse<GroupResponse>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = groupService.fetchGroup()
                Timber.tag("Group").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("GroupErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("GroupErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }
}