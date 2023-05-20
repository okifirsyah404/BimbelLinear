package com.okifirsyah.bimbellinear.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserModel(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String?,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @ColumnInfo(name = "group")
    @field:SerializedName("kelompok")
    val group: String? = null,

    @ColumnInfo(name = "group_type")
    @field:SerializedName("tipe")
    val groupType: String? = null,
) : Parcelable
