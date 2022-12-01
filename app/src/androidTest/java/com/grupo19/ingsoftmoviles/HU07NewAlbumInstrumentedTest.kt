package com.grupo19.ingsoftmoviles

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
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
class HU07NewAlbumInstrumentedTest {

    @get:Rule()
    var mActivityRuler: ActivityTestRule<HomeActivity> = ActivityTestRule(
        HomeActivity::class.java)
    private var mActivity: HomeActivity? = null

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

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
    fun test_createAlbum() {onView(withId(R.id.my_drawer_layout))
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
            .inRoot(RootMatchers.withDecorView(not(`is`(mActivity!!.window.decorView))))
            .perform(click())

        onView(withId(R.id.spinnerRecordLabel))
            .perform(click(), closeSoftKeyboard());

        onView(withText("EMI"))
            .inRoot(RootMatchers.withDecorView(not(`is`(mActivity!!.window.decorView))))
            .perform(click())

        onView(withId(R.id.buttonCreateAlbum))
            .perform(click())

        Thread.sleep(2000);

    }


}