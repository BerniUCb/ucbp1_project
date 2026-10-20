package org.ucb.appp1

import android.app.Application
import org.ucb.appp1.di.initKoinAndroid
import org.ucb.appp1.scheduler.LogScheduler

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
        LogScheduler(this).schedulePeriodicalUpload()
    }
}