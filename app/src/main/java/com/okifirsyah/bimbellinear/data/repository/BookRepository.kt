package com.okifirsyah.bimbellinear.data.repository

import com.okifirsyah.bimbellinear.data.model.BookModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.source.BookDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class BookRepository(private val datasource: BookDataSource) {
    suspend fun fetchBooks(): Flow<ApiResponse<BaseListResponse<BookModel>>> {
        return datasource.fetchBooks().flowOn(Dispatchers.IO)
    }

}