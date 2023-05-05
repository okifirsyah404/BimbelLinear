package com.okifirsyah.bimbellinear.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserModel(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int?,

)
