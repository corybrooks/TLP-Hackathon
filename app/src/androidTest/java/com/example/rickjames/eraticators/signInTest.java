package com.example.rickjames.eraticators;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.rickjames.eraticators.controller.RegistrationActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


/**
 * Created by christinefeng on 11/14/17.
 */

@RunWith(AndroidJUnit4.class)
public class signInTest {
    @Rule
    public final IntentsTestRule<RegistrationActivity> mActivityRule = new IntentsTestRule<>(RegistrationActivity.class);



    @Test
    public void CheckNull() {
        onView(withId(R.id.SignUp)).perform(click());
        onView(withText("Email and/or password cannot be empty."))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}
