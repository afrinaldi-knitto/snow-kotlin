package com.nalldev.snow.presentation.di

import com.nalldev.snow.presentation.presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}