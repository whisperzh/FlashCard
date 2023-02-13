package com.bignerdranch.android.flashcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val TAG = "QuizViewModel"
private val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
private val CURRENT_QUESTIONS_INDEX_KEY = "CURRENT_QUESTIONS_INDEX_KEY"
private val CURRENT_CORRECT_QUESTIONS_NUMBER_INDEX_KEY = "CURRENT_CORRECT_QUESTIONS_NUMBER_INDEX_KEY"

class QuestionViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val firstOperandLowerBound = 1
    private val firstOperandUpperBound = 99
    private val secondOperandLowerBound = 1
    private val secondOperandUpperBound = 20
    var currentQuestionIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?:0
        private set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    var questions: MutableList<Question>
        get() = savedStateHandle.get(CURRENT_QUESTIONS_INDEX_KEY) ?:mutableListOf()
        private set(value) = savedStateHandle.set(CURRENT_QUESTIONS_INDEX_KEY, value)
    var correctAnswerCount: Int
        get() = savedStateHandle.get(CURRENT_CORRECT_QUESTIONS_NUMBER_INDEX_KEY) ?:0
        private set(value) = savedStateHandle.set(CURRENT_CORRECT_QUESTIONS_NUMBER_INDEX_KEY, value)


    fun generateQuestions(){
        var questionList = mutableListOf<Question>()
        for (i in 1..10){
            val firstOperand = getRandomNumber(firstOperandLowerBound,firstOperandUpperBound)
            val secondOperand = getRandomNumber(secondOperandLowerBound,secondOperandUpperBound)
            if ((0..1).random() == 0){
                questionList.add(Question(firstOperand,secondOperand,"+", firstOperand+secondOperand))
            }else{
                questionList.add(Question(firstOperand,secondOperand,"-", firstOperand-secondOperand))
            }
        }
        questions = questionList
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

    fun getCorrectAnswer(): Int{
        return questions[currentQuestionIndex].answer
    }

    fun getCurrentQuestion(): Question{
        if (currentQuestionIndex < questions.size && currentQuestionIndex>=0){
            return questions.get(currentQuestionIndex)
        }else{
            throw java.lang.IndexOutOfBoundsException()
        }
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