package com.bignerdranch.android.flashcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class LoginText(private val savedStateHandle: SavedStateHandle):ViewModel(){
    var userName:String
        get() = savedStateHandle.get("USERNAME")?:""
        set(value) = savedStateHandle.set("USERNAME", value)

    var password:String
        get() = savedStateHandle.get("PASSWORD")?:""
        set(value) = savedStateHandle.set("PASSWORD", value)

//    fun setUserNameAndPassWord(username:String,  password:String){
//        this.userName=username
//        this.password =password
//    }

//    fun setUserName(username:String){
//        un=username
//    }
//
//    fun setPassWord(password:String){
//        pwd=password
//    }
//    fun getUserName(): String {
//      return un
//    }
//    fun getPassword():String{
//        return pwd
//    }
}
