package com.grupo19.ingsoftmoviles

import android.view.Gravity
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HU03ArtistListInstrumentedTest {

    @get:Rule()
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun test_scrollableArtists_andShowArtistDetail() {
        onView(withId(R.id.my_drawer_layout))
            .check(ViewAssertions.matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open())

        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.artistListFragment))

        onView(withId(R.id.artist_recycler_view))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(swipeUp())
            .perform(swipeDown())
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArtistAdapter.ArtistViewHolder>
                    (0, click()))

    }


}