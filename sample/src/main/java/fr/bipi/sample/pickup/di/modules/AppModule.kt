package fr.bipi.sample.pickup.di.modules

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import fr.bipi.sample.pickup.app.coroutine.AppDispatchers
import fr.bipi.sample.pickup.app.coroutine.AppDispatchersImpl
import fr.bipi.sample.pickup.data.AppSettings
import fr.bipi.sample.pickup.data.sources.settings.AppSettingsSource
import org.koin.dsl.module

val appModule by lazy {
    module {
        // --- Android ---
        single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }

        // --- Settings ---
        single<AppSettings> { AppSettingsSource(get()) }

        // --- Coroutines ---
        single<AppDispatchers> { AppDispatchersImpl() }
    }
}
