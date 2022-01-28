package fr.bipi.sample.pickup.ui.settings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import fr.bipi.sample.pickup.app.coroutine.AppDispatchers
import fr.bipi.sample.pickup.data.LogDataSource
import fr.bipi.test.robolectric.RobolectricTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.amshove.kluent.`should be empty`
import org.amshove.kluent.`should match`
import org.amshove.kluent.`should not be empty`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.get
import timber.log.Timber
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class SettingsDataSourceViewModelTest : RobolectricTest() {

    private lateinit var sut: AppSettingsViewModel
    private lateinit var logProvider: LogDataSource

    // We need this when dealing with livedata
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // We are using the koin definition initialized by App, running by Robolectric
        logProvider = get()
        sut = AppSettingsViewModel(
            logProvider,
            object : AppDispatchers {
                override val main: MainCoroutineDispatcher
                    get() = Dispatchers.Main
                override val io: CoroutineDispatcher
                    get() = TestCoroutineDispatcher()
            }
        )
    }

    @Test
    fun koin() {
        get<AppSettingsViewModel>()
    }

    @Test
    fun exportLogs() {
        // Logging into file initialized by App and Robolectric
        Timber.v("Test")

        // Without exporting log, folder should be empty
        (
            logProvider.getLogFolderForExport().listFiles()
                ?: throw NullPointerException("No files found in ${logProvider.getLogFolderForExport().absolutePath}")
            ).`should be empty`()

        val test = sut.exportState.test()

        sut.exportLogs()

        test.awaitValue(1, TimeUnit.SECONDS)
            .assertValue(AppSettingsViewModel.ExportLogState.Done)

        // after exporting logs, folder should not be empty
        val files = logProvider.getLogFolderForExport().listFiles()
            ?: throw NullPointerException("No files found in ${logProvider.getLogFolderForExport().absolutePath}")

        files.apply {
            `should not be empty`()
            get(0).readText().`should match`("^.*Test\n.*Copying .* to .*\n")
        }
    }

    @Test
    fun clearLogs() {
        // Logging into file initialized by App and Robolectric
        Timber.v("Test")

        sut.clearLogs()

        val test = sut.exportState.test()

        sut.exportLogs()

        test.awaitValue(1, TimeUnit.SECONDS)
            .assertValue(AppSettingsViewModel.ExportLogState.Done)

        // after exporting logs, folder should not be empty
        val files = logProvider.getLogFolderForExport().listFiles()
            ?: throw NullPointerException("No files found in ${logProvider.getLogFolderForExport().absolutePath}")
        files.apply {
            `should not be empty`()
            get(0).readText().`should match`("^.* deleted\n.*Copying .* to .*\n")
        }
    }
}
