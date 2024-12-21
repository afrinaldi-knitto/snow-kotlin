package com.nalldev.snow.data.network

import com.nalldev.snow.data.model.WsResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NetworkDataSource(
    private val client: HttpClient,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun connect() = flow {
        client.webSocket(
            method = HttpMethod.Get,
            host = "192.168.20.27",
            port = 8180,
            path = "/"
        ) {
            while (true) {
                val receiver = receiveDeserialized<WsResponse>()
                println("SEND DATA : ${receiver.jumlahData}")
                emit(receiver.jumlahData)
            }
        }
    }.flowOn(ioDispatcher)

    fun close() {
        client.close()
    }
}