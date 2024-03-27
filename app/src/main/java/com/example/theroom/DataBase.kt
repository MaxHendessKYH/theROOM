package com.example.theroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase ()
{
    abstract fun userDao() :UserDao

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDataBase(context: Context): DataBase
        {
            return INSTANCE ?: synchronized(this){
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "users"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}