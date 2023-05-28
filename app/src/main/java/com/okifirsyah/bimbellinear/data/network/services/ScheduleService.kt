package com.okifirsyah.bimbellinear.data.network.services

import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import retrofit2.http.GET

interface ScheduleService {
    @GET("jadwal")
    suspend fun getSchedule(): BaseListResponse<ScheduleModel>
}