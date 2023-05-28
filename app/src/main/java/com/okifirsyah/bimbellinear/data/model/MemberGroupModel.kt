package com.okifirsyah.bimbellinear.data.model

import com.google.gson.annotations.SerializedName

data class MemberGroupModel(
    @field:SerializedName("siswa_id")
    val id: String? = null,
    @field:SerializedName("nama")
    val name: String? = null,
    @field:SerializedName("status")
    val memberStatus: String? = null,
)
