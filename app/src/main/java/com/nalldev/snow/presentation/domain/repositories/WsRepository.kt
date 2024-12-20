package com.nalldev.snow.presentation.domain.repositories

import kotlinx.coroutines.flow.Flow

interface WsRepository {
    fun connect() : Flow<Int>
    fun close()
}