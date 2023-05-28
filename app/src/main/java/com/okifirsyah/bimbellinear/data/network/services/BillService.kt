package com.okifirsyah.bimbellinear.data.network.services

import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import retrofit2.http.GET

interface BillService {
    @GET("tagihan")
    suspend fun fetchBill(): BaseListResponse<BillModel>
}