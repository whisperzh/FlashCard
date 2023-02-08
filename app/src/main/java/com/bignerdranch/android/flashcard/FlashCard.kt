package com.bignerdranch.android.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.flashcard.databinding.ActivityFlashCardBinding

class FlashCard : AppCompatActivity() {
    private lateinit var binding:ActivityFlashCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)
        binding=ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}