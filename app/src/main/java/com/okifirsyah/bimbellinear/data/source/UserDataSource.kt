package com.okifirsyah.bimbellinear.data.source

import com.okifirsyah.bimbellinear.data.local.dao.ScheduleDao
import com.okifirsyah.bimbellinear.data.local.dao.UserDao
import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.request.ChangePasswordBody
import com.okifirsyah.bimbellinear.data.network.request.LoginBody
import com.okifirsyah.bimbellinear.data.network.request.OtpBody
import com.okifirsyah.bimbellinear.data.network.request.ResetOtpBody
import com.okifirsyah.bimbellinear.data.network.request.ResetPasswordBody
import com.okifirsyah.bimbellinear.data.network.response.SignInResponse
import com.okifirsyah.bimbellinear.data.network.response.SupportContactResponse
import com.okifirsyah.bimbellinear.data.network.services.UserService
import com.okifirsyah.bimbellinear.utils.extensions.createResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection

class UserDataSource(
    private val userService: UserService,
    private val userDao: UserDao,
    private val scheduleDao: ScheduleDao
) {
    suspend fun login(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<SignInResponse>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.login(LoginBody(email, password))
                Timber.tag("Login").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Login").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("LoginErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("LoginErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun fetchUserProfile(): Flow<ApiResponse<BaseResponse<UserModel>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)

                val response = userService.getProfile()
                Timber.tag("Profile").d(response.toString())

                if (userDao.isUserIsExist(response.data?.id!!)) {
                    userDao.updateUser(
                        response.data.name ?: "-",
                        response.data.email ?: "-",
                        response.data.name ?: "-",
                        response.data.group ?: "-",
                        response.data.id,
                    )
                } else {
                    userDao.deleteUser()
                    userDao.insertUser(response.data)
                }

                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                Timber.tag("ProfileErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun getOtpChangePassword(): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.getOtpChangePassword()
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Otp").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("OtpErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("OtpErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun sendOtpChangePassword(otp: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.sendOtpChangePassword(OtpBody(otp.toInt()))
                Timber.tag("Otp").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Otp").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("OtpErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("OtpErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun sendChangePassword(newPassword: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.changePassword(ChangePasswordBody(newPassword))
                Timber.tag("ChangePassword").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("ChangePassword").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("ChangePasswordErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("ChangePasswordErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun getSupportContact(): Flow<ApiResponse<BaseResponse<SupportContactResponse>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.getSupportContact()
                Timber.tag("SupportContact").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("SupportContact").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("SupportContactErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("SupportContactErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun logout(): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.logout()
                Timber.tag("Logout").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Logout").d(response.toString())
                    userDao.deleteUser()
                    scheduleDao.deleteAllSchedules()
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("LogoutErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("LogoutErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun getOtpResetPassword(email: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.getOtpResetPassword(ResetOtpBody(email))
                Timber.tag("Otp").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Otp").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("OtpErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("OtpErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun sendOtpResetPassword(otp: String): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.sendOtpResetPassword(OtpBody(otp.toInt()))
                Timber.tag("Otp").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("Otp").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("OtpErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("OtpErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }

    suspend fun sendResetPassword(
        email: String,
        newPassword: String
    ): Flow<ApiResponse<BaseResponse<Nothing>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = userService.resetPassword(ResetPasswordBody(email, newPassword))
                Timber.tag("ResetPassword").d(response.toString())
                if (response.status == HttpURLConnection.HTTP_OK) {
                    Timber.tag("ResetPassword").d(response.toString())
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                Timber.tag("ResetPasswordErrorException").d(e.toString())
                if (e is HttpException) {
                    val response = e.createResponse()
                    Timber.tag("ResetPasswordErrorException").d(response.toString())
                    emit(ApiResponse.Error(response?.message ?: ""))
                } else {
                    emit(ApiResponse.Error(e.message ?: ""))
                }
            }
        }
    }
}