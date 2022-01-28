package fr.bipi.sample.pickup.data.sources.settings

import fr.bipi.sample.pickup.BuildConfig
import fr.bipi.sample.pickup.BuildConfig.DEBUG
import fr.bipi.test.robolectric.RobolectricTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class SettingsDataSourceImplTest : RobolectricTest() {

    lateinit var appSettings: AppSettingsSource

    @Before
    fun setUp() {
        appSettings = AppSettingsSource(
            testDiUtils.provideSharedPreferences(),
        )
    }

    @Test
    fun getVersion() {
        appSettings.version.shouldBeEqualTo(BuildConfig.VERSION_NAME + (if (DEBUG) " debug" else ""))
    }
}
