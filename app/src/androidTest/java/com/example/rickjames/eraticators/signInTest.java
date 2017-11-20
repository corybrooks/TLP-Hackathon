package com.example.rickjames.eraticators;

import android.support.test.espresso.Espresso;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.matchers.JUnitMatchers.*;

import android.support.test.rule.ActivityTestRule;

import com.example.rickjames.eraticators.controller.AddRatActivity;
import com.example.rickjames.eraticators.controller.RatActivity;
import com.example.rickjames.eraticators.controller.UserActivity;
import com.example.rickjames.eraticators.controller.RegistrationActivity;
import com.example.rickjames.eraticators.model.UserType;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by christinefeng on 11/14/17.
 */

@RunWith(AndroidJUnit4.class)
public class signInTest {
    @Rule
    public IntentsTestRule<RegistrationActivity> mActivityRule = new IntentsTestRule<>(RegistrationActivity.class);
    String email = "repeatemail@gmail.com";
    @Test
    public void CheckAllNull() {
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckEmailNull() {
        onView(withId(R.id.Names)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckOnlyEmail() throws InterruptedException {
        onView(withId(R.id.inputtedEmail)).perform(typeText("test@test.com"), closeSoftKeyboard());
        Thread.sleep(100);
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckOnlyPassword() {
        onView(withId(R.id.inputtedPassword)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckPasswordNull() {
        onView(withId(R.id.Names)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.inputtedEmail)).perform(typeText("test@test.com"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    public void CheckNameNull() {
        onView(withId(R.id.inputtedEmail)).perform(typeText("test@test.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("testtest"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Authentication failed."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckInvalidEmail() {
        onView(withId(R.id.Names)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.inputtedEmail)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("testtest"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Authentication failed."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckOnlyName() {
        onView(withId(R.id.Names)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    // DELETE USER AND ADMIN AFTER EVERY TEST OR THIS WON'T WORK
    @Test
    public void CheckUserSuccess() {
        onView(withId(R.id.Names)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.inputtedEmail)).perform(typeText("user@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("testtest"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("User registration successful!"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAdminSuccess() {
        onView(withId(R.id.Names)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.userType)).perform(click());
        onData(allOf(is(instanceOf(UserType.class)), is(UserType.ADMIN))).perform(click());
        onView(withId(R.id.inputtedEmail)).perform(typeText("admin@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("testtest"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Admin registration successful!"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckRepeatEmail() { //Use an email you know is already in database!
        onView(withId(R.id.Names)).perform(typeText("Christine Feng"), closeSoftKeyboard());
        onView(withId(R.id.inputtedEmail)).perform(typeText("chfeng15@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("testtest"), closeSoftKeyboard());
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Authentication failed."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


}
