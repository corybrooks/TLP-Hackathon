package com.example.rickjames.eraticators;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToHolder;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
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
 * Created by Samir Ahmed on 11/14/2017.
 */

@RunWith(AndroidJUnit4.class)

public class UserActivityTest {

    /** this line is preferred way to hook up to activity */
    @Rule
    public IntentsTestRule<UserActivity> mActivityRule = new IntentsTestRule<>(UserActivity.class);


    @Test
    public void CheckButtonPress() {
        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();

    }

    @Test
    public void CheckButtonPress2() {
        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();
    }


    public void CheckButtonPress3() {
        onView(withId(R.id.RatList)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        intended(hasComponent(RatActivity.class.getName()));
        Espresso.pressBack();
    }
}
