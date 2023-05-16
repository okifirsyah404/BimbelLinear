package com.okifirsyah.bimbellinear.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import org.koin.dsl.module

fun preferenceModule(pref: DataStore<Preferences>) = module {
    single {
        pref
    }

    single {
        AppPreferences.getInstance(get())
    }

}