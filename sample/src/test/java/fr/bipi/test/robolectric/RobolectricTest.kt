package fr.bipi.test.robolectric

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import timber.log.Timber

/**
 * Base class extended by every Robolectric test in this project.
 * <p>
 * Robolectric tests are done in a single thread !
 */
@RunWith(RobolectricTestRunner::class)
@Config(
    packageName = "fr.bipi.sample.pickup",
    instrumentedPackages = [
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"
    ]
)
abstract class RobolectricTest : BaseTest() {

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            ShadowLog.stream = System.out
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
        }
    }

    @Before
    fun roboSetUp() {
    }

    @After
    fun roboTearDown() {
        // Robolectric will start App class in @Before method, then we need to uproot all trees here
        Timber.uprootAll()
    }
}
