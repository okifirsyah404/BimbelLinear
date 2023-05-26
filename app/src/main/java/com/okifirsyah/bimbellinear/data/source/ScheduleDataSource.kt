package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.local.dao.ScheduleDao
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.network.services.ScheduleService
import com.okifirsyah.bimbellinear.utils.extensions.createResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber

class ScheduleDataSource(
    private val scheduleService: ScheduleService,
    private val scheduleDao: ScheduleDao
) {
    suspend fun fetchSchedules(): Flow<ApiResponse<BaseListResponse<ScheduleModel>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = scheduleService.getSchedule()
                Timber.tag("Schedule").d(response.toString())

                scheduleDao.deleteAllSchedules()
                scheduleDao.insertAllSchedules(response.data ?: listOf())
                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                Timber.tag("ScheduleErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("ScheduleErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }
}