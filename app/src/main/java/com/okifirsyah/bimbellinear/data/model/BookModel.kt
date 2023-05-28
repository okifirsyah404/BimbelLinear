package com.okifirsyah.bimbellinear.data.model

import com.google.gson.annotations.SerializedName

data class BookModel(
    @field:SerializedName("modul_id")
    val id: String? = null,
    @field:SerializedName("nama_modul")
    val title: String? = null,
    @field:SerializedName("url")
    val fileUrl: String? = null,
    @field:SerializedName("thumbnail")
    val image: String? = null,
    @field:SerializedName("nama_mapel")
    val subTitle: String? = null,
)