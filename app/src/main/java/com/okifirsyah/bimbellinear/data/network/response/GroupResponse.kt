package com.okifirsyah.bimbellinear.data.network.response

import com.google.gson.annotations.SerializedName
import com.okifirsyah.bimbellinear.data.model.MemberGroupModel

data class GroupResponse(
    @field:SerializedName("kelompok_id")
    val id: String? = null,
    @field:SerializedName("kelompok")
    val group: String? = null,
    @field:SerializedName("tipe")
    val type: String? = null,
    @field:SerializedName("kelas")
    val grade: String? = null,
    @field:SerializedName("anggota")
    val member: List<MemberGroupModel>? = null
)
