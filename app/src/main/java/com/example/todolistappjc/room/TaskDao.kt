package com.example.todolistappjc.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insert(task:TaskEntity)

    @Delete
    fun delete(task:TaskEntity)




}