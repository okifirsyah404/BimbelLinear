package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.source.ScheduleDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ScheduleRepository(private val dataSource: ScheduleDataSource) {
    suspend fun fetchSchedules(): Flow<ApiResponse<BaseListResponse<ScheduleModel>>> {
        return dataSource.fetchSchedules().flowOn(Dispatchers.IO)
    }
}