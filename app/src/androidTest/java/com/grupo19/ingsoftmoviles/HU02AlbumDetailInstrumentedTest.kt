package com.grupo19.ingsoftmoviles

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import com.grupo19.ingsoftmoviles.ui.adapters.AlbumAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HU02AlbumDetailInstrumentedTest {

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
    fun test_scrollableAlbums_andShowAlbumDetail() {
        onView(withId(R.id.albums_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>
                    (0, click()))
        composeTestRule
            .onNode(hasText("Tracks"))
    }


}