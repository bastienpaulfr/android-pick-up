package fr.bipi.sample.pickup

import android.app.Application
import fr.bipi.sample.pickup.data.LogDataSource
import fr.bipi.sample.pickup.di.log.timberLogger
import fr.bipi.sample.pickup.di.modules.appModule
import fr.bipi.sample.pickup.di.modules.dataModule
import fr.bipi.sample.pickup.di.modules.domainModule
import fr.bipi.sample.pickup.di.modules.scopesModule
import fr.bipi.sample.pickup.di.modules.uiModule
import fr.bipi.sample.pickup.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDi()
        setupLog()
    }

    private fun setupDi() {
        startKoin {
            timberLogger()
            androidContext(this@App)
            modules(appModule)
            modules(dataModule)
            modules(domainModule)
            modules(scopesModule)
            modules(uiModule)
            modules(viewModelModule)
        }
    }

    private fun setupLog() {
        val logDataSource: LogDataSource = GlobalContext.get().get()
        logDataSource.plant(this)
    }
}
