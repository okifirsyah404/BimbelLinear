package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.ScheduleRepository
import com.okifirsyah.bimbellinear.data.source.ScheduleDataSource
import org.koin.dsl.module

val scheduleModule = module {
    factory { ScheduleRepository(get()) }
    single { ScheduleDataSource(get()) }
}