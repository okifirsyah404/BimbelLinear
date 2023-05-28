package com.okifirsyah.bimbellinear.data.network.response

import com.google.gson.annotations.SerializedName

data class SupportContactResponse(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("nama")
    val name: String? = null,
    @field:SerializedName("nomor_telepon")
    val phoneNumber: String? = null,
)