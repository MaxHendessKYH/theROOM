package com.example.theroom

import android.content.Context
import androidx.lifecycle.LiveData

class UserRepository(context: Context, private  val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user:User)
    {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user:User)
    {
        userDao.deleteUser(user)
    }

    suspend fun updateUser(user:User)
    {
        userDao.updateUser(user)
    }
}