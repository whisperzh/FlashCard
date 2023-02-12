package com.bignerdranch.android.flashcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question
    (val firstOperand: Int, val secondOperand: Int, val operation: String,val answer: Int): Parcelable
