package com.okifirsyah.bimbellinear.di

import com.okifirsyah.bimbellinear.presentation.MainViewModel
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.OnBoardingViewModel
import com.okifirsyah.bimbellinear.presentation.view.profile.ProfileViewModel
import com.okifirsyah.bimbellinear.presentation.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { OnBoardingViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

}