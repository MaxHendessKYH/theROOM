package com.example.theroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
    @Update
    suspend fun updateUser(user:User)
    @Delete
    suspend fun deleteUser(user: User)
    @Insert
    suspend fun addUser(user: User)
}