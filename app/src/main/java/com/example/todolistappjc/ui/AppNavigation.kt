package com.example.todolistappjc.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolistappjc.TaskViewModel
import com.example.todolistappjc.room.TaskEntity
import com.example.todolistappjc.ui_state.UiState

@Composable
fun AppNavigation(navController: NavHostController, taskViewModel: TaskViewModel) {


    NavHost(navController = navController, startDestination = "home_screen") {

        composable("home_screen")
         {
            val refresh = it.arguments?.getBoolean("refresh")
            HomeScreen(taskViewModel, navController)

        }

        composable(
            "add_task",
        ) {

            AddTaskScreen(taskViewModel,navController)
        }

    }


}

@Composable
fun HomeScreen(viewModel: TaskViewModel,navController: NavHostController) {




    val allTaskState by viewModel.allTasksLiveData.observeAsState()


    LaunchedEffect(key1 = true){
            viewModel.getAllTasks()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (allTaskState == null) {
                Text(text = "No tasks available")
            }
            else if( allTaskState is UiState.StateReady){
                val tasks= (allTaskState as UiState.StateReady<List<TaskEntity>>).data

                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(tasks){
                        task ->
                        Text(text = task.info)

                    }
                }

            }

        }

        FloatingActionButton(onClick = {
                                       navController.navigate("add_task")

        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(30.dp),
            backgroundColor = Color.Blue,
            contentColor = Color.White) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
            )
        }


    }


}

@Composable
fun AddTaskScreen(viewModel: TaskViewModel,navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var taskTextState by rememberSaveable() {
            mutableStateOf("")
        }

        Column() {
            OutlinedTextField(value = taskTextState, onValueChange = {
                taskTextState = it
            }, label = {
                Text(text = "Input a task")
            })

            Button(onClick = {
                             viewModel.addTask(TaskEntity(0,taskTextState,false))
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top=10.dp)){
                Text(text = "Add Task")
            }

            Button(onClick = {
                navController.navigate("home_screen?refresh=${false}")
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(top=10.dp)){
                Text(text = "Done")
            }

        }





    }

}