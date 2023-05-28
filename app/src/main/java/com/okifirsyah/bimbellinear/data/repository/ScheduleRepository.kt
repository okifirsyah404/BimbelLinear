package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.source.ScheduleDataSource
import com.okifirsyah.bimbellinear.data.source.ScheduleLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ScheduleRepository(
    private val remoteDataSource: ScheduleDataSource,
    private val localDataSource: ScheduleLocalDataSource
) {
    suspend fun fetchSchedules(): Flow<ApiResponse<BaseListResponse<ScheduleModel>>> {
        return remoteDataSource.fetchSchedules().flowOn(Dispatchers.IO)
    }

    suspend fun fetchLocalSchedules(): Flow<ApiResponse<BaseListResponse<ScheduleModel>>> {
        return localDataSource.fetchLocalSchedules().flowOn(Dispatchers.IO)
    }
}