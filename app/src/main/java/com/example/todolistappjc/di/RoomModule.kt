package com.example.todolistappjc.di

import android.content.Context
import androidx.room.Room
import com.example.todolistappjc.TaskRepository
import com.example.todolistappjc.room.TaskDao
import com.example.todolistappjc.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun getDB(@ApplicationContext appContext:Context): TaskDatabase {

        return Room.databaseBuilder(appContext,TaskDatabase::class.java,
        "task-database").build()

    }

    @Singleton
    @Provides
    fun getDao(roomDatabase: TaskDatabase): TaskDao{

        return roomDatabase.taskDao()

    }

    @Singleton
    @Provides
    fun getRepository(taskDao: TaskDao):TaskRepository{


        return TaskRepository(taskDao)
    }

}

