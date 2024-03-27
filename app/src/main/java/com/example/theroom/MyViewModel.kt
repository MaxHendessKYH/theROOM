package com.example.theroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MyViewModel(application: Application) : AndroidViewModel(application){
    private val repository: UserRepository
    private val allUsers: LiveData<List<User>>

    init {
        val userDao = DataBase.getDataBase(application).userDao()
        repository = UserRepository(application, userDao)
        allUsers = repository.allUsers
    }
    suspend fun updateUser(user: User){
        repository.updateUser(user)
    }
    suspend fun addUser(user: User){
        repository.addUser(user)
    }
    suspend fun deleteUser(user: User){
        repository.deleteUser(user)
    }
    fun getAllUsers(): LiveData<List<User>>{
        return repository.allUsers
    }
}