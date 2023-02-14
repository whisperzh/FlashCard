package com.bignerdranch.android.flashcard

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test
import org.junit.Assert.*
private const val TAG = "FlashCard: FlashCardTest"

@RunWith(AndroidJUnit4::class)
class FlashCardTest {
    private lateinit var scenario: ActivityScenario<FlashCard>
    @Before
    fun setUp() {
        scenario = launch(FlashCard::class.java)
    }


    @Test
    fun testRotation(){
        onView(withId(R.id.generateButton)).perform(click())
        onView(withId(R.id.answerText)).perform(ViewActions.clearText())
        onView(withId(R.id.answerText)).perform(ViewActions.typeText("13"))
        scenario.recreate()
        onView(withId(R.id.answerText)).check(matches(withText("13")))
    }


    @Test
    fun integerAnswerWillWork() {
        onView(withId(R.id.generateButton)).perform(click())
        repeat(2){
            onView(withId(R.id.answerText)).perform(ViewActions.clearText())
            onView(withId(R.id.answerText)).perform(ViewActions.typeText("20"))
            onView(withId(R.id.answerSubmitButton)).perform(click())
        }
    }

    @Test
    fun nonIntegerAnswerWillNotWork() {
        onView(withId(R.id.generateButton)).perform(click())
        repeat(2){
            onView(withId(R.id.answerText)).perform(ViewActions.clearText())
            onView(withId(R.id.answerText)).perform(ViewActions.typeText("non digit"))
            onView(withId(R.id.answerSubmitButton)).perform(click())
        }
    }

    @Test
    fun emptyAnswerWillNotWork() {
        onView(withId(R.id.generateButton)).perform(click())
        repeat(2){
            onView(withId(R.id.answerText)).perform(ViewActions.clearText())
            onView(withId(R.id.answerText)).perform(ViewActions.typeText(""))
            onView(withId(R.id.answerSubmitButton)).perform(click())
        }
    }

    @Test
    fun generateQuestionClickButtonWillGenerateTenQuestions() {
        onView(withId(R.id.generateButton)).perform(click())
        scenario.onActivity {
            assertEquals(it.getQuestionVM().questions.size, 10)
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }


}