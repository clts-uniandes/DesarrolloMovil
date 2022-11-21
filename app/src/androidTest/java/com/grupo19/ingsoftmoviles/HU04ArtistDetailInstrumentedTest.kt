package com.grupo19.ingsoftmoviles

import androidx.compose.ui.test.hasText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import com.grupo19.ingsoftmoviles.ui.adapters.AlbumAdapter
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistAdapter
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
        onView(withId(R.id.artistListFragment))
            .perform(swipeUp())
            .perform(swipeDown())
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArtistAdapter.ArtistViewHolder>
                    (0, click()))
        onView(withId(R.id.artist_description)).check(matches(withText("")))
        onView(withId(R.id.artist_description)).check(matches(isDisplayed()))

    }


}