package com.mohitum.androidmvpkotlindemo.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.mohitum.androidmvpkotlindemo.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    /**
     * Check for the Activity UI labels
     */
    @Test
    @Throws(InterruptedException::class)
    fun testMainActivityUILabels() {
        onView(withId(R.id.demo_info_label))
                .check(matches(withText(mActivityRule.activity.getString(R.string.demo_info_text))))
        onView(withId(R.id.movieNameEditText))
                .check(matches(withHint(mActivityRule.activity.getString(R.string.movie_name_edit_text_hint))))
        onView(withId(R.id.error_label))
                .check(matches(withText(mActivityRule.activity.getString(R.string.search_error_no_text))))
        onView(withId(R.id.searchButton))
                .check(matches(withText(mActivityRule.activity.getString(R.string.button_search_text))))
    }
}