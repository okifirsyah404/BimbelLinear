package com.okifirsyah.bimbellinear

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.okifirsyah.bimbellinear.di.networkModule
import com.okifirsyah.bimbellinear.di.preferenceModule
import com.okifirsyah.bimbellinear.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

private val Context.dataStore by preferencesDataStore(name = "settings")

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApp)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    preferenceModule(dataStore),
//                list of modules
                ),
            )

        }
    }
}