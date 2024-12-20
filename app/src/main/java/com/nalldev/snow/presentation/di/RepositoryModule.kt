package com.nalldev.snow.presentation.di

import com.nalldev.snow.presentation.data.network.NetworkDataSource
import com.nalldev.snow.presentation.data.repositories.WsRepositoryImpl
import com.nalldev.snow.presentation.domain.repositories.WsRepository
import org.koin.dsl.module

fun provideWsRepository(networkDataSource: NetworkDataSource) : WsRepository {
    return WsRepositoryImpl(networkDataSource)
}

val repositoryModule = module {
    single { provideWsRepository(get()) }
}