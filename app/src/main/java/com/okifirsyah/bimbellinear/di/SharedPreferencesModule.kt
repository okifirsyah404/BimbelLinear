package com.okifirsyah.bimbellinear.di

import com.okifirsyah.bimbellinear.utils.SharedPrefenrenceManager
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single {
        return@single SharedPrefenrenceManager.getInstance(get())
    }
}