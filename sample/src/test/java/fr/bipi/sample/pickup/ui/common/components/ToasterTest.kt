package fr.bipi.sample.pickup.ui.common.components

import fr.bipi.test.robolectric.RobolectricTest
import org.junit.Test
import org.koin.core.component.get

class ToasterTest : RobolectricTest() {
    @Test
    fun koin() {
        get<Toaster>()
    }
}
