package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.GroupResponse
import com.okifirsyah.bimbellinear.data.source.GroupDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GroupRepository(private val dataSource: GroupDataSource) {
    suspend fun fetchGroupData(): Flow<ApiResponse<BaseResponse<GroupResponse>>> {
        return dataSource.fetchGroup().flowOn(Dispatchers.IO)
    }
}