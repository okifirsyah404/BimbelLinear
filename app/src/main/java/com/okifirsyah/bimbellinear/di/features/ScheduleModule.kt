package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.ScheduleRepository
import com.okifirsyah.bimbellinear.data.source.ScheduleDataSource
import com.okifirsyah.bimbellinear.data.source.ScheduleLocalDataSource
import org.koin.dsl.module

val scheduleModule = module {
    factory { ScheduleRepository(get(), get()) }
    single { ScheduleDataSource(get(), get()) }
    single { ScheduleLocalDataSource(get()) }
}