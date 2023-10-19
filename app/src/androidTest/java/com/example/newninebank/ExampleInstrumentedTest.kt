package com.example.newninebank

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
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
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("Johnny"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("12345678909"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.user_input_button)).perform(ViewActions.click())
        onView(withId(R.id.user_input_button)).perform(ViewActions.click())
        onView(withId(R.id.types_of_account_recycler)).perform(ViewActions.swipeRight())
        onView(withId(R.id.types_of_account_recycler)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.types_of_account_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<TypesOfAccountAdapter.TypesOfAccountViewHolder>(2,ViewActions.click()))
        onView(withId(R.id.button_confirm)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("john.doe@gmail.com"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("Password1234*"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
    }

    @Test
    fun testClickonMultipleTypesOfAccount(){
        onView(withId(R.id.button_open_account)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("Johnny"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("12345678909"))
        onView(withId(R.id.button_send)).perform(ViewActions.click())
        onView(withId(R.id.user_input_button)).perform(ViewActions.click())
        onView(withId(R.id.user_input_button)).perform(ViewActions.click())
        onView(withId(R.id.types_of_account_recycler)).perform(ViewActions.swipeRight())
        onView(withId(R.id.types_of_account_recycler)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.types_of_account_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<TypesOfAccountAdapter.TypesOfAccountViewHolder>(2,ViewActions.click()))
        onView(withId(R.id.types_of_account_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<TypesOfAccountAdapter.TypesOfAccountViewHolder>(1,ViewActions.click()))
        onView(withId(R.id.types_of_account_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<TypesOfAccountAdapter.TypesOfAccountViewHolder>(0,ViewActions.click()))
        onView(withId(R.id.types_of_account_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<TypesOfAccountAdapter.TypesOfAccountViewHolder>(1,ViewActions.click()))
        onView(withId(R.id.button_confirm)).perform(ViewActions.click())
        onView(withId(R.id.edit_text_chat_input)).perform(ViewActions.typeText("john.doe@gmail.com"))


    }
}