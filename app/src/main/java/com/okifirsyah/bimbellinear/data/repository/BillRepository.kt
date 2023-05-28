package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.source.BillDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class BillRepository(private val dataSource: BillDataSource) {
    suspend fun fetchBills(): Flow<ApiResponse<BaseListResponse<BillModel>>> {
        return dataSource.fetchBills().flowOn(Dispatchers.IO)
    }
}