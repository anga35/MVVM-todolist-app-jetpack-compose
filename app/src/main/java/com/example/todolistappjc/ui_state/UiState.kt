package com.example.todolistappjc.ui_state

sealed class UiState<out T>{
    class StateLoading:UiState<Nothing>()
    class StateReady<out R>(val data:R):UiState<R>()


}
