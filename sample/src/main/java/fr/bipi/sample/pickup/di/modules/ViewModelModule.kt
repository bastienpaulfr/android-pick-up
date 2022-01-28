package fr.bipi.sample.pickup.di.modules

import fr.bipi.sample.pickup.ui.settings.AppSettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule by lazy {
    module {
        viewModel { AppSettingsViewModel(get(), get()) }
    }
}
