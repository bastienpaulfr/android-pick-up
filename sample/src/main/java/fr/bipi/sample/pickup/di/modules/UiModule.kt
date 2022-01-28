package fr.bipi.sample.pickup.di.modules

import fr.bipi.sample.pickup.ui.common.components.Toaster
import org.koin.dsl.module

val uiModule by lazy {
    module {
        // --- Toaster ---
        single { Toaster(get()) }
    }
}
