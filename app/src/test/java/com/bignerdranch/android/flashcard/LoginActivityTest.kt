package com.bignerdranch.android.flashcard

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

class LoginActivityTest{
    @Test
    fun logintext_function_check(){
        val savedStateHandle = SavedStateHandle(mapOf("USERNAME" to "Admin"))
        val model = LoginText(savedStateHandle)
        assertEquals("Admin", model.userName)
    }

}