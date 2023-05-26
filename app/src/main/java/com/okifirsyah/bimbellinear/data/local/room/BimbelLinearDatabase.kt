package com.okifirsyah.bimbellinear.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.okifirsyah.bimbellinear.data.local.dao.ScheduleDao
import com.okifirsyah.bimbellinear.data.local.dao.UserDao
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.model.UserModel


@Database(
    entities = [UserModel::class, ScheduleModel::class],
    version = 1,
    exportSchema = false
)

abstract class BimbelLinearDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getScheduleDao(): ScheduleDao
}