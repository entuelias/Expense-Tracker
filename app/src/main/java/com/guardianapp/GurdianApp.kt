package com.guardianapp

import android.app.Application
import com.guardianapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GuardianApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GuardianApp)
            modules(viewModelModule)
        }
    }
}