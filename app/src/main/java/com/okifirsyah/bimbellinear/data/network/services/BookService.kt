package com.okifirsyah.bimbellinear.data.network.services

import com.okifirsyah.bimbellinear.data.model.BookModel
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import retrofit2.http.GET

interface BookService {
    @GET("modul")
    suspend fun fetchBooks(): BaseListResponse<BookModel>
    
}