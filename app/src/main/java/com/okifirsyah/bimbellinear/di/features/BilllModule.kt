package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.BillRepository
import com.okifirsyah.bimbellinear.data.source.BillDataSource
import org.koin.dsl.module

val billModule = module {
    factory { BillRepository(get()) }
    single { BillDataSource(get()) }
}