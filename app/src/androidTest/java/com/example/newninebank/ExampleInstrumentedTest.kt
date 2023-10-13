package com.example.newninebank

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newninebank.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest{
    @get:Rule
    val activity = ActivityScenarioRule( MainActivity::class.java)

    @Test
    fun testOpenAccount(){
        onView(withId(R.id.button_open_account)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("John"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("40178797805"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.user_input_button)).perform(ViewActions.click())
        onView(withId(R.id.types_of_account_recycler)).perform(ViewActions.swipeRight())
        onView(withId(R.id.types_of_account_recycler)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.modal_bottom_sheet)).perform(ViewActions.swipeDown())
    }
}