package com.okifirsyah.bimbellinear.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttendanceModel(
    val id: Int,
    val subject: String,
    val room: String,
    val teacher: String,
    val date: String,
    val status: String,
) : Parcelable