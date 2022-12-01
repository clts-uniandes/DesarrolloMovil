package com.grupo19.ingsoftmoviles

import android.view.Gravity
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HU08NewAlbumWithTrackInsTest {

    @get:Rule()
    var mActivityRuler: ActivityTestRule<HomeActivity> = ActivityTestRule(
        HomeActivity::class.java)
    private var mActivity: HomeActivity? = null

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setActivity() {
        mActivity = mActivityRuler.activity
    }
    fun setUp() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun test_associateTrack() {

        onView(withId(R.id.my_drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open());

        onView(withText("Crear Álbum"))
            .perform(click());

        onView(withId(R.id.editTextName))
            .perform(typeText("Mi album Prueba"))

        onView(withId(R.id.editTextCoverUrl))
            .perform(typeText("https://upload.wikimedia.org/wikipedia/en/6/69/ImagineCover.jpg"))

        onView(withId(R.id.editTextDate))
            .perform(typeText("2000-02-02"), closeSoftKeyboard())

        onView(withId(R.id.editTextDescription))
            .perform(typeText("Mi Album Prueba es especial"), closeSoftKeyboard())

        onView(withId(R.id.spinnerGenre))
            .perform(click(), closeSoftKeyboard());

        onView(withText("Rock"))
            .inRoot(withDecorView(not(`is`(mActivity!!.window.decorView))))
            .perform(click())

        onView(withId(R.id.spinnerRecordLabel))
            .perform(click(), closeSoftKeyboard());

        onView(withText("EMI"))
            .inRoot(withDecorView(not(`is`(mActivity!!.window.decorView))))
            .perform(click())

        onView(withId(R.id.buttonCreateAlbum))
            .perform(click())

        Thread.sleep(2000);

        onView(withText(R.string.button_ok_pupup_album)).perform(click())

        onView(withId(R.id.track_name))
            .perform(typeText("Track test"), closeSoftKeyboard())

        onView(withId(R.id.track_duration))
            .perform(typeText("00:03:56"), closeSoftKeyboard())

        onView(withId(R.id.button_asociate))

        Thread.sleep(2000);
        composeTestRule
            .onNode(hasText("Tracks"))

        composeTestRule
            .onNode(hasText("Track test"))

        Thread.sleep(2000);

    }
}