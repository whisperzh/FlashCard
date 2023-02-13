package com.bignerdranch.android.flashcard

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*

import org.junit.Test

class QuestionViewModelTest {

    @Test
    fun generateQuestions() {
        val savedStateHandle = SavedStateHandle()
        val questionViewModel = QuestionViewModel(savedStateHandle)
        questionViewModel.generateQuestions()
        val questions = questionViewModel.questions
        assertEquals(questions.size, 10)
        for (question in questions){
            assertTrue(question.firstOperand in 1..99)
            assertTrue(question.secondOperand in 1..20)
            assertTrue(question.operation.equals("+") || question.operation.equals("-"))
        }
    }

    @Test
    fun test_operationWorksCorrectly(){
        val savedStateHandle = SavedStateHandle()
        val questionViewModel = QuestionViewModel(savedStateHandle)
        questionViewModel.generateQuestions()
        val questions = questionViewModel.questions
        assertEquals(questions.size, 10)
        for (question in questions){
            if (question.operation.equals("+")){
                assertTrue(question.firstOperand+question.secondOperand == question.answer)
            }else{
                assertTrue(question.firstOperand-question.secondOperand == question.answer)
            }
        }
    }

    @Test
    fun test_goToNextQuestionCorrectly(){
        val savedStateHandle = SavedStateHandle()
        val questionViewModel = QuestionViewModel(savedStateHandle)
        assertTrue(questionViewModel.currentQuestionIndex == 0)
        questionViewModel.moveToNextQuestion()
        assertTrue(questionViewModel.currentQuestionIndex == 1)
        repeat(10){
            questionViewModel.moveToNextQuestion()
        }
        assertTrue(questionViewModel.currentQuestionIndex == 11)
        assertThrows(java.lang.IndexOutOfBoundsException::class.java){
            questionViewModel.questions.get(questionViewModel.currentQuestionIndex)
        }
    }

    @Test
    fun test_restartCleanUpCorrectly(){
        val savedStateHandle = SavedStateHandle()
        val questionViewModel = QuestionViewModel(savedStateHandle)
        questionViewModel.generateQuestions()
        questionViewModel.moveToNextQuestion()
        questionViewModel.resetAllDataToStartGame()
        assertEquals(questionViewModel.currentQuestionIndex,0)
        assertEquals(questionViewModel.questions.size, 0)
        assertEquals(questionViewModel.correctAnswerCount, 0)
    }




}
