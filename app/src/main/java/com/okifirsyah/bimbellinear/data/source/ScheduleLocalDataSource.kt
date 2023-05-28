package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.local.dao.ScheduleDao
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScheduleLocalDataSource(private val scheduleDao: ScheduleDao) {
    fun fetchLocalSchedules(): Flow<ApiResponse<BaseListResponse<ScheduleModel>>> {
        return flow {
            try {
                val schedules = scheduleDao.getAllSchedules()
                emit(ApiResponse.Success(BaseListResponse(503, "Offline mode", schedules)))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message ?: ""))
            }
        }
    }
}