package com.bignerdranch.android.flashcard

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var gameStarted: Boolean = false
    private var userName: String = ""

    fun setGameStartedToTrue(){
        gameStarted = true
    }

    fun setUserName(name: String){
        userName = name
    }

    fun resetAllDataToStartGame(){
        gameStarted = false
        userName = ""
    }

    fun getUserName(): String{
        return userName
    }

    fun isGameStarted(): Boolean{
        return gameStarted
    }

}