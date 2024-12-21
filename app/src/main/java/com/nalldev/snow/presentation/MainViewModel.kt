package com.nalldev.snow.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.snow.domain.usecase.WsUseCase
import com.nalldev.snow.utils.Notification
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val wsUseCase: WsUseCase
) : ViewModel() {
    private val _isPermissionGranted = MutableStateFlow(false)

    var isWsConnected by mutableStateOf(false)
        private set

    private var job : Job? = null
    private var counter : Int = 0

    fun doWsConnect() = viewModelScope.launch {
        isWsConnected = true
        job?.cancel()
        job = launch {
            wsUseCase.wsConnect().combine(_isPermissionGranted) { message, isGranted ->
                Pair(message, isGranted)
            }.collect { (message, isGranted) ->
                if (isGranted) {
                    Notification.sendNotification(application, "Notifikasi ke - ${++counter}", "MESSAGE SOCKET : $message")
                }
                print("DATA : $message : $isGranted")
            }
        }
    }

    fun doWsClose() = viewModelScope.launch {
        isWsConnected = false
        job?.cancel()
        job = null
    }

    fun updatePermissionStatus(isGranted: Boolean) {
        _isPermissionGranted.update { isGranted }
    }

    override fun onCleared() {
        super.onCleared()
        wsUseCase.wsClose()
    }
}