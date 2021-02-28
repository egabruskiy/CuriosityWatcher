package com.egabruskiy.curiositywatcher

import android.app.Application
import com.egabruskiy.curiositywatcher.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App :Application(){
    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
    }


    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                appModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }


    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}