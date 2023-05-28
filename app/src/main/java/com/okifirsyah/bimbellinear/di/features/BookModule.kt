package com.okifirsyah.bimbellinear.di.features

import com.okifirsyah.bimbellinear.data.repository.BookRepository
import com.okifirsyah.bimbellinear.data.source.BookDataSource
import org.koin.dsl.module

val bookModule = module {
    factory { BookRepository(get()) }
    single { BookDataSource(get()) }
}