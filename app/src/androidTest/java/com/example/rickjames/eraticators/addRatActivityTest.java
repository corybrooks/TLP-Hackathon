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
 * Created by RickJames on 11/11/2017.
 */


@RunWith(AndroidJUnit4.class)
public class addRatActivityTest {


    /** this line is preferred way to hook up to activity */
    @Rule
    public IntentsTestRule<AddRatActivity> mActivityRule = new IntentsTestRule<>(AddRatActivity.class);

    @Test
    public void checkAddNull() {
        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("Error attempting to view rat on map. Are latitude and longitude valid?"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddAddrNull() {

        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());

        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddDateNull() {

        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddNameNull() {

        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddBoroughNull() {

        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddTypeNull() {

        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddCityNull() {

        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddZipNull() {

        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddLatNull() {

        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckAddLongNull() {

        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());
        onView(withText("One Value is empty"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkAddWorks() {
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());

        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());

        intended(hasComponent(UserActivity.class.getName()));

        onView(withId(R.id.AddRat)).perform(click());



    }

    @Test
    public void checkAddLatFail() {
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());

        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("For"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());

        onView(withText("Error attempting to view rat on map. Are latitude and longitude valid?"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        //intended(hasComponent(AddRatActivity.class.getName()));
    }

    @Test
    public void checkAddLongFail() {
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());

        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("For"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());

        onView(withText("Error attempting to view rat on map. Are latitude and longitude valid?"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        //intended(hasComponent(AddRatActivity.class.getName()));
    }

    @Test
    public void checkAddFail() {
        onView(withId(R.id.inputtedDate)).perform(ViewActions.scrollTo()).perform(typeText("11/23/20"), closeSoftKeyboard());
        onView(withId(R.id.inputtedName)).perform(ViewActions.scrollTo()).perform(typeText("9999999999995"), closeSoftKeyboard());
        onView(withId(R.id.inputtedBorough)).perform(ViewActions.scrollTo()).perform(typeText("Borough"), closeSoftKeyboard());
        onView(withId(R.id.inputtedType)).perform(ViewActions.scrollTo()).perform(typeText("Type"), closeSoftKeyboard());
        onView(withId(R.id.inputtedCity)).perform(ViewActions.scrollTo()).perform(typeText("City"), closeSoftKeyboard());
        onView(withId(R.id.inputtedZip)).perform(ViewActions.scrollTo()).perform(typeText("31097"), closeSoftKeyboard());
        onView(withId(R.id.inputtedAddress)).perform(ViewActions.scrollTo()).perform(typeText("HiltonHead"), closeSoftKeyboard());

        onView(withId(R.id.inputtedLatitude)).perform(ViewActions.scrollTo()).perform(typeText("For"), closeSoftKeyboard());
        onView(withId(R.id.inputtedLongitude)).perform(ViewActions.scrollTo()).perform(typeText("For"), closeSoftKeyboard());

        onView(withId(R.id.addRat)).perform(ViewActions.scrollTo()).perform(click());

        onView(withText("Error attempting to view rat on map. Are latitude and longitude valid?"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        //intended(hasComponent(AddRatActivity.class.getName()));


    }

//    @Test
//    public void checkAdd() {
//        //type 23 into the first edit box
//        onView(withId(R.id.number1Field)).perform(typeText("23"), closeSoftKeyboard());
//        //type 45 into the second edit box
//        onView(withId(R.id.number2Field)).perform(typeText("45"), closeSoftKeyboard());
//        //click on the spinner to select it
//        onView(withId(R.id.spinner)).perform(click());
//        //no access the adapter to look for the chosen item (+ in this case) and select it
//        onData(allOf(is(instanceOf(String.class)), is("+"))).perform(click());
//        //now click the execute button
//        onView(withId(R.id.executeButton)).perform(click());
//
//        //finally we grab the result text and make sure it matches our result
//        onView(withId(R.id.resultText2)).check(matches(withText("68")));
//        Espresso.pressBack();
//    }



}
