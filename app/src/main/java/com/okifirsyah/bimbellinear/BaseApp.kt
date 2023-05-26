package com.okifirsyah.bimbellinear

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.datastore.preferences.preferencesDataStore
import com.okifirsyah.bimbellinear.di.features.billModule
import com.okifirsyah.bimbellinear.di.features.bookModule
import com.okifirsyah.bimbellinear.di.features.groupModule
import com.okifirsyah.bimbellinear.di.features.scheduleModule
import com.okifirsyah.bimbellinear.di.features.userModule
import com.okifirsyah.bimbellinear.di.localModule
import com.okifirsyah.bimbellinear.di.networkModule
import com.okifirsyah.bimbellinear.di.preferenceModule
import com.okifirsyah.bimbellinear.di.viewModelModule
import net.gotev.uploadservice.UploadServiceConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

private val Context.dataStore by preferencesDataStore(name = "settings")

class BaseApp : Application() {

    companion object {
        const val notificationChannelID = "BimbelLinearChannel"
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = notificationChannelID
            val channelName = "Bimbel Linear Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        createNotificationChannel()

        UploadServiceConfig.initialize(
            context = this,
            defaultNotificationChannel = notificationChannelID,
            debug = BuildConfig.DEBUG
        )

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApp)
            modules(
                listOf(
                    networkModule,
                    localModule,
                    userModule,
                    scheduleModule,
                    groupModule,
                    billModule,
                    bookModule,
                    preferenceModule(dataStore),
                    viewModelModule,
                ),
            )

        }
    }
}