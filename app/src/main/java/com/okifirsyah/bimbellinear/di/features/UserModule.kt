package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.UserRepository
import com.okifirsyah.bimbellinear.data.source.UserDataSource
import org.koin.dsl.module

val userModule = module {
    factory { UserRepository(get()) }
    single { UserDataSource(get()) }
}