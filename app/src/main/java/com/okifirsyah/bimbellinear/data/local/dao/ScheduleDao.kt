package com.okifirsyah.bimbellinear.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okifirsyah.bimbellinear.data.model.ScheduleModel

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSchedules(schedules: List<ScheduleModel>)

    @Query("SELECT * FROM schedule")
    suspend fun getAllSchedules(): List<ScheduleModel>

    @Query("DELETE FROM schedule")
    suspend fun deleteAllSchedules()

}