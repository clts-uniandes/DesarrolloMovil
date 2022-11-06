package com.grupo19.ingsoftmoviles

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import com.grupo19.ingsoftmoviles.ui.MainActivity
import com.grupo19.ingsoftmoviles.ui.adapters.AlbumAdapter
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumInstrumentedTest {

    @get:Rule()
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun test_isAlbumListVisible_onAppLaunch() {
        onView(withId(R.id.albums_recycler_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_selectAlbumItem_showAlbumDetail() {
        onView(withId(R.id.albums_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(1, click()))

        onView(withId(R.id.album_detail))
            .check(matches(withText(containsString("Poeta del pueblo"))))
    }

    @Test
    fun test_selectAlbumItem_showAlbumDetail_pressBack_selectANewAlbumItem_showAlbumDetail() {
        onView(withId(R.id.albums_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(1, click()))

        onView(withId(R.id.album_detail))
            .check(matches(withText(containsString("Poeta del pueblo"))))

        pressBack()

        onView(withId(R.id.albums_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(2, click()))

        onView(withId(R.id.album_detail))
            .check(matches(withText(containsString("A Night at the Opera"))))

    }

}