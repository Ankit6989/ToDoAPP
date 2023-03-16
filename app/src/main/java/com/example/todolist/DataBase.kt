package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entity::class],version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun dao():DAO

    companion object {
        @Volatile
        private var INSTANCE  : DataBase? = null

        fun getDatabase(context: Context): DataBase {

            if (INSTANCE == null) {

                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java, "to_do"
                    ).build()
                }

            }
            return INSTANCE!!
        }
    }

}