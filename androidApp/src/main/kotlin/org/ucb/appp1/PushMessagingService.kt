package org.ucb.appp1

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        println("FCM nuevo token = $token")
    }

    // Solo se llama con la app abierta: en segundo plano la notificación la muestra Firebase
    override fun onMessageReceived(message: RemoteMessage) {
        val notification = message.notification ?: return
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notification.title)
            .setContentText(notification.body)
            .setAutoCancel(true)
        getSystemService(NotificationManager::class.java)
            .notify(System.currentTimeMillis().toInt(), builder.build())
    }

    companion object {
        const val CHANNEL_ID = "general"
    }
}
