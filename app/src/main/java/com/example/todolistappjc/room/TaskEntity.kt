package com.example.todolistappjc.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
class TaskEntity(
    @PrimaryKey(autoGenerate = true) val uid:Int,
    @ColumnInfo(name = "info") val info:String,
    @ColumnInfo(name="done") val done:Boolean
)

