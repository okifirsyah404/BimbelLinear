package com.okifirsyah.bimbellinear.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class ScheduleModel(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("jadwal_id")
    val id: String,
    @field:SerializedName("mapel")
    val subject: String,
    @field:SerializedName("ruang")
    val room: String,
    @field:SerializedName("tentor")
    val teacher: String,
    @field:SerializedName("sesi")
    val session: String,
    @field:SerializedName("jam")
    val time: String,
    @field:SerializedName("hari")
    val day: String,
) : Parcelable
