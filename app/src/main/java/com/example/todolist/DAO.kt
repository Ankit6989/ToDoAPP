package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete FROM to_do")
    suspend fun deleteAll()

    @Query("Select * FROM to_do")
     fun getTask():LiveData<List<CardInfo>>


}