package com.nalldev.snow.presentation

import android.app.Application
import com.nalldev.snow.presentation.di.commonModule
import com.nalldev.snow.presentation.di.dataSourceModule
import com.nalldev.snow.presentation.di.networkModule
import com.nalldev.snow.presentation.di.repositoryModule
import com.nalldev.snow.presentation.di.useCaseModule
import com.nalldev.snow.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Snow : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@Snow)
            modules(
                listOf(
                    commonModule,
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}