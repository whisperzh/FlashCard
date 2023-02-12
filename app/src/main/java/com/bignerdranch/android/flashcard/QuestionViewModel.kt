package com.bignerdranch.android.flashcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val TAG = "QuizViewModel"
private val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
private val CURRENT_QUESTIONS_INDEX_KEY = "CURRENT_QUESTIONS_INDEX_KEY"

class QuestionViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val firstOperandLowerBound = 1
    private val firstOperandUpperBound = 99
    private val secondOperandLowerBound = 1
    private val secondOperandUpperBound = 20
    private var currentQuestionIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    private var questions: MutableList<Question>
        get() = savedStateHandle.get(CURRENT_QUESTIONS_INDEX_KEY) ?:mutableListOf<Question>()
        set(value) = savedStateHandle.set(CURRENT_QUESTIONS_INDEX_KEY, value)
    private var correctAnswerCount: Int = 0


    fun generateQuestions(){
        for (i in 1..10){
            val firstOperand = getRandomNumber(firstOperandLowerBound,firstOperandUpperBound)
            val secondOperand = getRandomNumber(secondOperandLowerBound,secondOperandUpperBound)
            if ((0..1).random() == 0){
                questions.add(Question(firstOperand,secondOperand,"+", firstOperand+secondOperand))
            }else{
                questions.add(Question(firstOperand,secondOperand,"-", firstOperand-secondOperand))
            }
        }
    }

    fun printAllQuestions(){
        for (q in questions){
            println(q)
        }
    }

    fun resetAllDataToStartGame(){
        currentQuestionIndex = 0
        questions.clear()
        correctAnswerCount = 0
    }

    fun getTotalQuestionNumber():Int{
        return questions.size
    }

    fun isLastQuestion(): Boolean{
        return currentQuestionIndex == questions.size-1
    }

    fun increaseCorrectCountByOne(){
        this.correctAnswerCount += 1
    }

    fun clearCorrectAnswer(){
        this.correctAnswerCount = 1
    }

    fun getCorrectAnswer(): Int{
        return questions[currentQuestionIndex].answer
    }

    fun getCurrentFirstOperand(): Int{
        print(questions[currentQuestionIndex].firstOperand)
        return questions.get(currentQuestionIndex).firstOperand
    }

    fun getCurrentSecondOperand(): Int{
        return questions.get(currentQuestionIndex).secondOperand
    }

    fun getCurrentOperator(): String{
        return questions.get(currentQuestionIndex).operation
    }


    fun moveToNextQuestion() {
        currentQuestionIndex += 1
    }

    fun getCorrectCounts(): Int{
        return correctAnswerCount
    }

    private fun getRandomNumber(lowerBound: Int, upperBound: Int): Int {
        return Random.nextInt(lowerBound, upperBound)
    }
}