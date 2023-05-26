package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.model.BookModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.network.services.BookService
import com.okifirsyah.bimbellinear.utils.extensions.createResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection

class BookDataSource(private val bookService: BookService) {
    suspend fun fetchBooks(): Flow<ApiResponse<BaseListResponse<BookModel>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = bookService.fetchBooks()
                Timber.tag("Book").d(response.data?.get(0)?.id.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("BookErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("BookErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }
}