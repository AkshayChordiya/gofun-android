package com.adityakamble49.ttl.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.ComponentNameMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.adityakamble49.ttl.ui.activity.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 *
 * @author Akshay Chordiya
 * @since 16-03-2017.
 * @version 1.0
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var intentsRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun openAboutTest() {
        openActionBarOverflowOrOptionsMenu(intentsRule.activity)
        onView(withText("About")).perform(click())
        intended(allOf(
                hasComponent(ComponentNameMatchers.hasClassName("com.adityakamble49.ttl.ui.activity.AboutActivity"))
        ))
    }

}