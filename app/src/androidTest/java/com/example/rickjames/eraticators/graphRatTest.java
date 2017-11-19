package com.example.rickjames.eraticators;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.example.rickjames.eraticators.controller.AddRatActivity;
import com.example.rickjames.eraticators.controller.GraphActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by corybrooks on 11/19/17.
 */

public class graphRatTest {

    @Rule
    public final IntentsTestRule<GraphActivity> mActivityRule = new IntentsTestRule<>(GraphActivity.class);

    @Test
    public void checkGotSnapshots() {
        //onView(withId(R.id.graphRats)).perform(click());
        //onView(withId(R.id.graphRats)).perform(click());
        onView(withText("Got snapshot from database"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNoDates() {
        onView(withId(R.id.graphRats)).perform(click());
        onView(withText("Both dates are empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNoStartDate() {
        onView(withId(R.id.startDate)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.graphRats)).perform(click());
        onView(withText("End date is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNoEndDate() {
        onView(withId(R.id.endDate)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.graphRats)).perform(click());
        onView(withText("Start date is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkValidDates() {
        onView(withId(R.id.startDate)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.endDate)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.graphRats)).perform(click());
        onView(withText("Valid dates"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}


