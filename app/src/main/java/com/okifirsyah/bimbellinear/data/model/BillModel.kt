package com.okifirsyah.bimbellinear.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BillModel(
    val id: Int,
    val period: String,
    val date: String,
    val total: Int,
    val status: String,
    val image: String? = null,
) : Parcelable
