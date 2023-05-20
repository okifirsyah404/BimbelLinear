package com.okifirsyah.bimbellinear.data.network.services

import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.request.LoginBody
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("login")
    suspend fun login(
        @Body request: LoginBody
    ): BaseResponse<SignInResponse>

    @GET("profile")
    suspend fun getProfile(): BaseResponse<UserModel>


}