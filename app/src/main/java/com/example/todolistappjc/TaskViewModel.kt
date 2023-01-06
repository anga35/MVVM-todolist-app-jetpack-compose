package com.example.todolistappjc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistappjc.room.TaskEntity
import com.example.todolistappjc.ui_state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    val taskRepository: TaskRepository

) : ViewModel() {

    private val _allTasksLiveData:MutableLiveData<UiState<List<TaskEntity>>> = MutableLiveData(null)
    val allTasksLiveData:LiveData<UiState<List<TaskEntity>>>
        get() = _allTasksLiveData

    private val _addTaskLiveData:MutableLiveData<UiState<Boolean>> = MutableLiveData()
    val addTasksLiveData:LiveData<UiState<Boolean>>
        get() = _addTaskLiveData


    fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.getTasks().collect {
                uiState->
                withContext(Dispatchers.Main){
                    _allTasksLiveData.value=uiState
                }

            }
        }


    }

    fun addTask(task:TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task).collect{
                uiState->

                withContext(Dispatchers.Main){
                    _addTaskLiveData.value=uiState
                }



            }

        }

    }


}