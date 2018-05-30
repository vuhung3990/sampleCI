package com.tux.sampelogin

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import android.widget.TextView
import com.tux.sampelogin.login.LoginActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    val loginActivity = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun should_show_invalid_email() {
        val invalidEmail = "dsaghdsa"

        // type invalid email
        onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText(invalidEmail))
        // click login
        onView(ViewMatchers.withId(R.id.login_btn)).perform(ViewActions.click())
        // should show email invalid error
        onErrorViewWithinTilWithId(R.id.textInputEmail).check(matches(withText(R.string.invalid_email)))
    }

    @Test
    fun should_show_invalid_password() {
        val validEmail = "saghdsa@dasdsa.com"
        val invalidPassword = "sashsda m sds"

        // type valid email
        onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText(validEmail))
        // type invalid password
        onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText(invalidPassword))
        // click login
        onView(ViewMatchers.withId(R.id.login_btn)).perform(ViewActions.click())
        // should show email invalid error
        onErrorViewWithinTilWithId(R.id.textInputPassword).check(matches(withText(R.string.invalid_password)))
    }

    @Test
    fun should_open_main_when_success() {
        val validEmail = "saghdsa@dasdsa.com"
        val validPassword = "sashsdamsds"

        // type valid email
        onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText(validEmail))
        // type valid password
        onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText(validPassword))
        // click login
        onView(ViewMatchers.withId(R.id.login_btn)).perform(ViewActions.click())
        // should show login success on center
        onView(withId(R.id.textView)).check(matches(withText(R.string.login_success)))
    }

    @Test
    fun should_show_signup() {
        // click on sign up
        onView(withId(R.id.register)).perform(ViewActions.click())
        // show sign up activity
        onView(withId(R.id.text)).check(matches(withText(R.string.sign_up)))
    }

    /*
               * Use this method to find the error view within the TextInputLayout. Useful for asseting that certain errors are displayed to the user
               */
    private fun onErrorViewWithinTilWithId(@IdRes textInputLayoutId: Int): ViewInteraction {
        return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), not(isAssignableFrom(EditText::class.java)), isAssignableFrom(TextView::class.java)))
    }
}
