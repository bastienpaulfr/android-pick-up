package fr.bipi.sample.pickup.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TemporaryFolder

class TestDiUtils(
    /**
     * Storage folder given by temporary folder junit rule
     */
    tempFolder: TemporaryFolder
) {
    fun provideContext(): Context = ApplicationProvider.getApplicationContext()

    fun provideSharedPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(provideContext())
}
