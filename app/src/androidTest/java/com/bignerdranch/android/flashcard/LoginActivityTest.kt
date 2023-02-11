package com.bignerdranch.android.flashcard

import org.junit.Assert.*
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    private lateinit var scenario: ActivityScenario<LoginActivity>
    @Before
    fun setUp() {
        scenario = launch(LoginActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun fully_auto_Login() {
        onView(withId(R.id.userNameEditText)).perform(typeText("Admin"))
        onView(withId(R.id.userNameEditText)).perform(pressBack())
        onView(withId(R.id.passwordEditText)).perform(typeText("123456"), click())
        onView(withId(R.id.passwordEditText)).perform(pressBack())
        scenario.recreate()
        onView(withId(R.id.submitButton)).perform(click())

    }
}