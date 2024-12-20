package com.nalldev.snow.presentation.di

import com.nalldev.snow.presentation.domain.usecase.WsClose
import com.nalldev.snow.presentation.domain.usecase.WsConnect
import com.nalldev.snow.presentation.domain.usecase.WsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { WsConnect(get()) }
    single { WsClose(get()) }
    single { WsUseCase(get(), get()) }
}