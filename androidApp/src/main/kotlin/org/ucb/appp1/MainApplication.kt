package org.ucb.appp1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import org.ucb.appp1.di.initKoinAndroid

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            PushMessagingService.CHANNEL_ID,
            "General",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }
}