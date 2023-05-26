package com.okifirsyah.bimbellinear.di

import android.app.Application
import androidx.room.Room
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.data.local.room.BimbelLinearDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { provideAuthService(get()) }
    single { provideScheduleService(get()) }

    factory { get<BimbelLinearDatabase>().getUserDao() }
    factory { get<BimbelLinearDatabase>().getScheduleDao() }

    fun provideDatabase(application: Application): BimbelLinearDatabase {
        return Room.databaseBuilder(
            application,
            BimbelLinearDatabase::class.java,
            BuildConfig.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(androidApplication()) }
}