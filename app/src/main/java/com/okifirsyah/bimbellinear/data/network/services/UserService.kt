package com.okifirsyah.bimbellinear.data.network.services

import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.request.ChangePasswordBody
import com.okifirsyah.bimbellinear.data.network.request.LoginBody
import com.okifirsyah.bimbellinear.data.network.request.OtpBody
import com.okifirsyah.bimbellinear.data.network.request.ResetOtpBody
import com.okifirsyah.bimbellinear.data.network.request.ResetPasswordBody
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import com.okifirsyah.bimbellinear.data.network.response.SupportContactResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @POST("login")
    suspend fun login(
        @Body request: LoginBody
    ): BaseResponse<SignInResponse>

    @GET("profile")
    suspend fun getProfile(): BaseResponse<UserModel>

    @GET("verifubah")
    suspend fun getOtpChangePassword(): BaseResponse<Nothing>

    @POST("verifubah")
    suspend fun sendOtpChangePassword(
        @Body request: OtpBody
    ): BaseResponse<Nothing>

    @POST("ubahpassword")
    suspend fun changePassword(
        @Body request: ChangePasswordBody
    ): BaseResponse<Nothing>

    @GET("support")
    suspend fun getSupportContact(): BaseResponse<SupportContactResponse>

    @GET("logout")
    suspend fun logout(): BaseResponse<Nothing>

    @PUT("veriflupa")
    suspend fun getOtpResetPassword(
        @Body request: ResetOtpBody
    ): BaseResponse<Nothing>

    @POST("veriflupa")
    suspend fun sendOtpResetPassword(
        @Body request: OtpBody
    ): BaseResponse<Nothing>

    @POST("lupapassword")
    suspend fun resetPassword(
        @Body request: ResetPasswordBody
    ): BaseResponse<Nothing>


}