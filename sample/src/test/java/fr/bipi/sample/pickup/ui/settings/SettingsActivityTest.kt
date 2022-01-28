package fr.bipi.sample.pickup.ui.settings

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import fr.bipi.test.robolectric.RobolectricTest
import org.junit.Rule
import org.junit.Test

class SettingsActivityTest : RobolectricTest() {

    @get:Rule
    var activityRule: ActivityScenarioRule<SettingsActivity> = ActivityScenarioRule(SettingsActivity::class.java)

    @Test
    fun `click on application should show application fragment`() {
        onView(withText("Application")).perform(click())
    }

    @Test
    fun `Launching header fragment without config should throw no exceptions`() {
        launchFragmentInContainer<SettingsActivity.HeaderFragment>(themeResId = android.R.style.Theme_Material_Light_DarkActionBar)
    }

    @Test
    fun `Launching app fragment without config should throw no exceptions`() {
        launchFragmentInContainer<SettingsActivity.AppFragment>(themeResId = android.R.style.Theme_Material_Light_DarkActionBar)
    }
}
