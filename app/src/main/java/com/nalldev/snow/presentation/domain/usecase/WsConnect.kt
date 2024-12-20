package com.nalldev.snow.presentation.domain.usecase

import com.nalldev.snow.presentation.domain.repositories.WsRepository
import kotlinx.coroutines.flow.Flow

class WsConnect(
    private val repository: WsRepository
) {
    operator fun invoke() : Flow<Int> = repository.connect()
}