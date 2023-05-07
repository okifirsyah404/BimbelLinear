package com.okifirsyah.bimbellinear.di

import com.okifirsyah.bimbellinear.presentation.MainViewModel
import com.okifirsyah.bimbellinear.presentation.view.attendance_detail.AttendanceDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}