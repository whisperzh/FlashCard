package com.bignerdranch.android.flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.bignerdranch.android.flashcard.databinding.ActivityFlashCardBinding
import com.bignerdranch.android.flashcard.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var username=""
    private var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)
        setupListeners()

    }

    fun validateId(): Boolean {
        username=binding.userNameEditText.text.toString()
        password=binding.passwordEditText.text.toString()
        if(username.equals("Admin")&& password.equals("123456"))
            return true
        return false
    }
    fun setupListeners(){
        binding.submitButton.setOnClickListener {
            if(validateId())
            {
                var it=Intent(this,FlashCard::class.java)
                startActivity(it);
                //start intent
            }else
            {
                Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

}