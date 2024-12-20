package com.nalldev.snow.presentation.domain.usecase

import com.nalldev.snow.presentation.domain.repositories.WsRepository

class WsClose(private val repository: WsRepository) {
    operator fun invoke() {
        repository.close()
    }
}