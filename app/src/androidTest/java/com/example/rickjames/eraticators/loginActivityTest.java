package com.example.rickjames.eraticators;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;

import com.example.rickjames.eraticators.controller.AddRatActivity;
import com.example.rickjames.eraticators.controller.RatActivity;
import com.example.rickjames.eraticators.controller.UserActivity;
import com.example.rickjames.eraticators.controller.LoginActivity;

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
 * Created by Josep on 11/14/2017.
 */
@RunWith(AndroidJUnit4.class)
public class loginActivityTest {
    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void CheckLoginNull() {
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Error logging in. Must input username and password."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginUserEmailNull() {
        onView(withId(R.id.inputtedPassword)).perform(typeText("bootybutt"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Error logging in. Must input username and password."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginUserPasswordNull() {
        onView(withId(R.id.inputtedEmail)).perform(typeText("joephobic@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Error logging in. Must input password."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginUserPasswordIncorrect() {
        onView(withId(R.id.inputtedEmail)).perform(typeText("joephobic@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("booootybutt"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Authentication failed."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}
