package org.ucb.appp1

import android.app.Application
import org.ucb.appp1.di.initKoinAndroid

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }
}