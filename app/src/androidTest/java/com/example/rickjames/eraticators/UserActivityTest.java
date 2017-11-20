package com.example.rickjames.eraticators;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.rickjames.eraticators.controller.RatActivity;
import com.example.rickjames.eraticators.controller.UserActivity;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Samir Ahmed on 11/14/2017.
 */

@RunWith(AndroidJUnit4.class)

public class UserActivityTest {

    /** this line is preferred way to hook up to activity */
    @Rule
    public IntentsTestRule<UserActivity> mActivityRule = new IntentsTestRule<>(UserActivity.class);


    @Test
    public void CheckButtonPress() {
        onView(withId(R.id.startDate)).perform(click());
       // intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();

    }

    @Test
    public void CheckButtonPress2() {
        onView(withId(R.id.endDate)).perform(click());
        // intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();

    }


    @Test
    public void CheckButtonPress3() {
        for(int i = 0; i < 500000000; i++) {

        }
        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        for(int i = 0; i < 500000000; i++) {

        }
        intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();
    }

    @Test
    public void CheckButtonPress4() {
        for(int i = 0; i < 500000000; i++) {

        }

        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        for(int i = 0; i < 500000000; i++) {

        }
        intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();
    }
//
//
//    public void CheckButtonPress4() {
//        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
//        intended(hasComponent(RatActivity.class.getName()));
//        Espresso.pressBack();
//    }
//
//
//
//    public void CheckButtonPress9() {
//        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));
//        intended(hasComponent(RatActivity.class.getName()));
//        Espresso.pressBack();
//    }
}
