package com.guardianapp.di

import com.guardianapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    // Other ViewModels will be added here later
}