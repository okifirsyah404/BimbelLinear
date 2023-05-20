package com.okifirsyah.bimbellinear.data.network.services

import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.GroupResponse
import retrofit2.http.GET

interface GroupService {
    @GET("kelompok")
    suspend fun fetchGroup(): BaseResponse<GroupResponse>
}