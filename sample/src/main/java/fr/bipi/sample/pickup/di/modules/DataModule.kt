package fr.bipi.sample.pickup.di.modules

import fr.bipi.sample.pickup.data.LogDataSource
import fr.bipi.sample.pickup.data.sources.log.LogDataSourceImpl
import org.koin.dsl.module

val dataModule by lazy {
    module {
        // --- Log provider ---
        single<LogDataSource> { LogDataSourceImpl() }
    }
}
