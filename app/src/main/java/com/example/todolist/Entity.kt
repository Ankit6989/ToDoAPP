package com.example.todolist

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "to_do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
                  val id: Int,
                  val title: String,
                  val priority: String)







