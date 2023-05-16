package com.okifirsyah.bimbellinear.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduleModel(
    val id: Int,
    val subject: String,
    val room: String,
    val teacher: String,
    val day: String,
    val time: String,
) : Parcelable
