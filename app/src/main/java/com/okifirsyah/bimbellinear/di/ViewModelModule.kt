package com.okifirsyah.bimbellinear.di

import com.okifirsyah.bimbellinear.presentation.MainViewModel
import com.okifirsyah.bimbellinear.presentation.view.bill.BillListViewModel
import com.okifirsyah.bimbellinear.presentation.view.bill_detail.BillDetailViewModel
import com.okifirsyah.bimbellinear.presentation.view.change_password.ChangePasswordViewModel
import com.okifirsyah.bimbellinear.presentation.view.group_info.GroupInfoViewModel
import com.okifirsyah.bimbellinear.presentation.view.home.HomeViewModel
import com.okifirsyah.bimbellinear.presentation.view.module_book.ModuleBookViewModel
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.OnBoardingViewModel
import com.okifirsyah.bimbellinear.presentation.view.otp.OtpViewModel
import com.okifirsyah.bimbellinear.presentation.view.profile.ProfileViewModel
import com.okifirsyah.bimbellinear.presentation.view.reset_password.ResetPasswordViewModel
import com.okifirsyah.bimbellinear.presentation.view.sign_in.SignInViewModel
import com.okifirsyah.bimbellinear.presentation.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SignInViewModel(get(), get()) }
    viewModel { SplashViewModel(get(), get()) }
    viewModel { OnBoardingViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { GroupInfoViewModel(get(), get()) }
    viewModel { BillListViewModel(get(), get()) }
    viewModel { BillDetailViewModel(get()) }
    viewModel { OtpViewModel(get()) }
    viewModel { ChangePasswordViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
    viewModel { ModuleBookViewModel(get()) }
}