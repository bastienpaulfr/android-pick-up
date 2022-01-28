package fr.bipi.test.robolectric

import fr.bipi.sample.pickup.di.TestDiUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import timber.log.Timber

abstract class BaseTest : KoinTest {

    @Rule
    @JvmField
    var tempFolder = TemporaryFolder()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Timber.v("Mocking ${clazz.qualifiedName}")
        Mockito.mock(clazz.java)
    }

    lateinit var testDiUtils: TestDiUtils

    @Before
    fun baseKoinSetUp() {
        testDiUtils = TestDiUtils(tempFolder)
    }

    @After
    fun baseKoinTearDown() {
        stopKoin()
    }
}
