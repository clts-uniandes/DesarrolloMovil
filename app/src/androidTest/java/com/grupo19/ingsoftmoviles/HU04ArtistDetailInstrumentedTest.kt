package com.grupo19.ingsoftmoviles

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistAdapter
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HU04ArtistDetailInstrumentedTest {

    @get:Rule()
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun test_scrollableAlbums() {
        onView(withId(R.id.my_drawer_layout))
            .check(matches(DrawerMatchers.isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open())

        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.artistListFragment))

        onView(withId(R.id.artist_recycler_view))
            .perform(swipeUp())
            .perform(swipeDown())
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArtistAdapter.ArtistViewHolder>
                    (0, click()))

        onView(withId(R.id.card_artist_detail_primary_title))
            .check(matches(withText(Matchers.containsString("Blades"))))

    }


}