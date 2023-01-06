package com.example.todolistappjc

import com.example.todolistappjc.room.TaskDao
import com.example.todolistappjc.room.TaskEntity
import com.example.todolistappjc.ui_state.UiState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    val taskDao: TaskDao
) {

    val j ="Yoo"
    fun getTasks() = flow<UiState<List<TaskEntity>>> {
        emit(UiState.StateLoading())
        val tasks= taskDao.getAll()
        emit(UiState.StateReady(tasks))
    }

    suspend fun addTask(task:TaskEntity) = flow<UiState<Boolean>> {

        emit(UiState.StateLoading())

        taskDao.insert(task)
        emit(UiState.StateReady(true))

    }

}