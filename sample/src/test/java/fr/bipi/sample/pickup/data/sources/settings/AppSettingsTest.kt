package fr.bipi.sample.pickup.data.sources.settings

import fr.bipi.sample.pickup.data.AppSettings
import fr.bipi.test.robolectric.RobolectricTest
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test
import org.koin.test.get

class AppSettingsTest : RobolectricTest() {
    @Test
    fun koin() {
        get<AppSettings>().shouldBeInstanceOf<AppSettingsSource>()
    }
}
