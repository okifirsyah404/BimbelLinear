package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.GroupRepository
import com.okifirsyah.bimbellinear.data.source.GroupDataSource
import org.koin.dsl.module

val groupModule = module {
    factory { GroupRepository(get()) }
    single { GroupDataSource(get()) }
}