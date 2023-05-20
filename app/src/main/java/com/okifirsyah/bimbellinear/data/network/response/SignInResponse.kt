package com.okifirsyah.bimbellinear.data.network.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @field:SerializedName("token")
    val token: String? = null
)