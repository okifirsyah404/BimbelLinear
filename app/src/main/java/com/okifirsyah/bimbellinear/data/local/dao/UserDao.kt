package com.okifirsyah.bimbellinear.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okifirsyah.bimbellinear.data.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertUser(user: UserModel)

    @Query("UPDATE user SET name = :name, email = :email, `group` = :group, group_type = :groupType WHERE id = :id")
    suspend fun updateUser(
        name: String,
        email: String,
        group: String,
        groupType: String,
        id: String
    )

    @Query("SELECT EXISTS(SELECT * FROM user WHERE id = :id)")
    suspend fun isUserIsExist(id: String): Boolean

    @Query("SELECT * FROM user")
    suspend fun getUser(): UserModel

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}