package com.nalldev.snow.presentation.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.snow.presentation.domain.usecase.WsUseCase
import com.nalldev.snow.presentation.utils.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val wsUseCase: WsUseCase
) : ViewModel() {
    private var counter = 0

    private val _wsMessage = MutableStateFlow(0)
    private val _isPermissionGranted = MutableStateFlow(false)

    fun doWsConnect() = viewModelScope.launch {
        wsUseCase.wsConnect().collect { message ->
            Notification.sendNotification(
                context = application,
                title = "Notifikasi counter ${++counter}",
                message = "Message from socket : $message"
            )
        }
    }

    fun doWsClose() = viewModelScope.launch {
        wsUseCase.wsClose()
    }

    fun updatePermissionStatus(isGranted: Boolean) {
        _isPermissionGranted.update { isGranted }
    }
}