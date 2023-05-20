package com.okifirsyah.bimbellinear.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import retrofit2.HttpException
import java.lang.reflect.Type

val gson = Gson()
val type: Type = object : TypeToken<BaseResponse<Any>>() {}.type

fun Exception.createResponse(): BaseResponse<Any>? {
    return when (this) {
        is HttpException -> {
            if (this.code() != 400) {
                gson.fromJson(response()?.errorBody()?.charStream(), type)
            } else {
                BaseResponse(
                    status = 0,
                    message = this.message ?: "",
                    data = null,
                )
            }
        }

        else -> {
            BaseResponse(
                status = 0,
                message = this.message ?: "",
                data = null,
            )
        }
    }
}