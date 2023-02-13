package com.bignerdranch.android.flashcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private val CURRENT_GAME_RUNNING_STATUS_INDEX_KEY = "CURRENT_GAME_RUNNING_STATUS_INDEX_KEY"
private val CURRENT_USER_NAME_INDEX_KEY = "CURRENT_USER_NAME_INDEX_KEY"
class GameViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var gameStarted: Boolean
        get() = savedStateHandle.get(CURRENT_GAME_RUNNING_STATUS_INDEX_KEY) ?:false
        private set(value) = savedStateHandle.set(CURRENT_GAME_RUNNING_STATUS_INDEX_KEY, value)
    private var username: String
        get() = savedStateHandle.get(CURRENT_USER_NAME_INDEX_KEY) ?:""
        set(value) = savedStateHandle.set(CURRENT_USER_NAME_INDEX_KEY, value)


    fun setGameStartedToTrue(){
        gameStarted = true
    }

    fun setUserName(name: String){
        username = name
    }

    fun resetAllDataToStartGame(){
        gameStarted = false
        username = ""
    }

    fun getUserName(): String{
        return username
    }

    fun isGameStarted(): Boolean{
        return gameStarted
    }

}