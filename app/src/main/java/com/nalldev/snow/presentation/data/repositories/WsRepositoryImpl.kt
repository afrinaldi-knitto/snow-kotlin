package com.nalldev.snow.presentation.data.repositories

import com.nalldev.snow.presentation.data.network.NetworkDataSource
import com.nalldev.snow.presentation.domain.repositories.WsRepository

class WsRepositoryImpl(
    private val networkDataSource : NetworkDataSource
) : WsRepository {
    override fun connect() = networkDataSource.connect()

    override fun close() {
        networkDataSource.close()
    }
}