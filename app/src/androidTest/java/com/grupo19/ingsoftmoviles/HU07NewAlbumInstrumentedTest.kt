package com.grupo19.ingsoftmoviles

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
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
    fun test_createAlbum() {

        onView(withId(R.id.nav_fragment))
            .perform(click())


        /*onView(withId(R.id.newAlbumActivity))
            .perform(click())

        onView(withId(R.id.editTextName))
            .perform(typeText("Mi album Prueba"))

        onView(withId(R.id.editTextCoverUrl))
            .perform(typeText("https://upload.wikimedia.org/wikipedia/en/6/69/ImagineCover.jpg"))

        onView(withId(R.id.editTextDate))
            .perform(typeText("2000-02-02"))

        onView(withId(R.id.editTextDescription))
            .perform(typeText("Mi Album Prueba es especial"))

        onView(withId(R.id.spinnerGenre))
            .perform(click())*/

        //onData(allOf()).inAdapterView(withId(R.id.spinnerGenre)).atPosition(1).perform(click())
        //onData(allOf(is(instanceOf(String.javaClass)), startsWith("A"))).perform(click())

        /*onView(withText("Classical")).perform(click())

        onView(withId(R.id.spinnerRecordLabel))
            .perform(click())

        onView(withText("EMI")).perform(click())

        onView(withId(R.id.buttonCreateAlbum))
            .perform(click())

        onView(withText(R.string.button_cancel_pupup_album))
            .perform(click())*/

    }


}