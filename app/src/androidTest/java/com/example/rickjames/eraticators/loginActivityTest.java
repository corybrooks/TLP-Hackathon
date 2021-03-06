package com.example.rickjames.eraticators;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.rickjames.eraticators.controller.LoginActivity;
import com.example.rickjames.eraticators.controller.UserActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Josep on 11/14/2017.
 */
@RunWith(AndroidJUnit4.class)
public class loginActivityTest {
    @Rule
    public final IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void CheckLoginNull() {
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Error logging in. Must input username and password."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginUserEmailNull() {
        onView(withId(R.id.inputtedPassword)).perform(typeText("testtest"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Error logging in. Must input username and password."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginUserPasswordNull() {
        onView(withId(R.id.inputtedEmail)).perform(typeText("testemail@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Error logging in. Must input password."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginUserPasswordIncorrect() {
        onView(withId(R.id.inputtedEmail)).perform(typeText("testemail@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("testestest"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Authentication failed."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void zzzCheckLoginCorrect() {
        onView(withId(R.id.inputtedEmail)).perform(typeText("jaja@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputtedPassword)).perform(typeText("456456"), closeSoftKeyboard());
        onView(withId(R.id.SignIn)).perform(click());
        onView(withText("Welcome jaja@gmail.com"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}
