package com.nalldev.snow.data.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nalldev.snow.R

class FCMService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
    }

    override fun onNewToken(token: String) {
        Log.e(TAG, "new FCM token created: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("onMessageReceived", "onMessageReceived : $remoteMessage")
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder =
            NotificationCompat.Builder(applicationContext, "1")
        notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.splash_icon)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun initNotificationChannel(notificationManager: NotificationManager) {
        notificationManager.createNotificationChannelIfNOtExists(
            channelId = "1",
            channelName = "Default"
        )
    }
}

fun NotificationManager.createNotificationChannelIfNOtExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}