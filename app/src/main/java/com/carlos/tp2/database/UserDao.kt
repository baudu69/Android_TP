package com.carlos.tp2.database

import androidx.room.*
import com.carlos.tp2.data.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long
    @Delete
    fun delete(user: User)
    @Update
    fun update(user: User)
    @Query("SELECT * from user WHERE id = :key")
    fun get(key: Long): User?
    @Query("SELECT * FROM user ORDER BY id DESC LIMIT 1")
    fun getLastUser(): User?

}