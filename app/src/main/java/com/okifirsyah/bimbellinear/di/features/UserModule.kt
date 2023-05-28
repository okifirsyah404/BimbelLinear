package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.UserRepository
import com.okifirsyah.bimbellinear.data.source.UserDataSource
import com.okifirsyah.bimbellinear.data.source.UserLocalDataSource
import org.koin.dsl.module

val userModule = module {
    factory { UserRepository(get(), get()) }
    single { UserDataSource(get(), get(), get()) }
    single { UserLocalDataSource(get()) }
}