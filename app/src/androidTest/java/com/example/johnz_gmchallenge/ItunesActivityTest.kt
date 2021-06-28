package com.example.johnz_gmchallenge

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.johnz_gmchallenge.ui.view.ItunesActivity
import org.junit.Rule
import org.junit.Test

class ItunesActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(ItunesActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_textInputEditText() {
        onView(withId(R.id.text_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_recyclerView() {
        onView(withId(R.id.text_edit_text)).perform(typeText("U2"), closeSoftKeyboard())
        onView(withId(R.id.btn_search)).perform(click())

        SystemClock.sleep(3000)

        onView(withId(R.id.rv_list)).check(matches(isDisplayed()))
    }
}