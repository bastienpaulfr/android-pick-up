package fr.bipi.sample.pickup.data.sources.log

import fr.bipi.sample.pickup.data.LogDataSource
import fr.bipi.test.robolectric.RobolectricTest
import org.amshove.kluent.`should be empty`
import org.amshove.kluent.`should end with`
import org.amshove.kluent.`should not be blank`
import org.amshove.kluent.`should not be empty`
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldMatch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.get
import timber.log.Timber
import java.io.File

class LogDataSourceImplTest : RobolectricTest() {

    private lateinit var sut: LogDataSourceImpl

    @Before
    fun before() {
        sut = LogDataSourceImpl()
    }

    @After
    fun after() {
        Timber.uprootAll()
    }

    @Test
    fun koin() {
        get<LogDataSource>().shouldBeInstanceOf(LogDataSourceImpl::class)
    }

    @Test
    fun getLogsFolder() {
        sut.getLogFolderForExport().apply {
            absolutePath.`should end with`("logs")
        }
    }

    @Test
    fun `getting log files without init should be empty`() {
        sut.getLogFiles().`should be empty`()
    }

    @Test
    fun `getting log files with init should not be empty`() {
        sut.plant(testDiUtils.provideContext())
        sut.getLogFiles().`should not be empty`()
    }

    @Test
    fun clearFileLog() {
        sut.plant(testDiUtils.provideContext())
        Timber.i("Log message")
        val list = ArrayList<File>().apply {
            addAll(sut.getLogFiles())
        }
        list[0].readText().`should not be blank`()
        sut.clearFileLog()
        list[0].readText().shouldMatch(".*LogDataSourceImpl.* deleted\n".toRegex())
        Timber.v("yoyoyoyo")
        list[0].readText().shouldMatch(".*LogDataSourceImpl.* deleted\n.*yoyoyoyo\n".toRegex())
    }

    @Test
    fun plant() {
        sut.plant(testDiUtils.provideContext())
        Timber.forest().`should not be empty`()
    }
}
