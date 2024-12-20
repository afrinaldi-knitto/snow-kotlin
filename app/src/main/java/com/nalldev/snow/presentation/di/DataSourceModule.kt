package com.nalldev.snow.presentation.di

import com.nalldev.snow.presentation.data.network.NetworkDataSource
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideNetworkDataSource(client: HttpClient, ioDispatcher: CoroutineDispatcher) : NetworkDataSource {
    return NetworkDataSource(client, ioDispatcher)
}

val dataSourceModule = module {
    single { provideNetworkDataSource(get(), get(named("IODispatcher"))) }
}