package org.ucb.appp1.scheduler

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


actual class LogScheduler actual constructor(
   context: Any?
) {
   private val androidContext = context as? Context
       ?: throw IllegalArgumentException("Context must be an instance of android.content.Context")
   private val LOG_WORKNAME = "logUploadWork"
   private val INTERVAL_MINUTES = 15L


   actual fun schedulePeriodicalUpload() {
       //PeriodicWorkRequest
       val logRequest = PeriodicWorkRequest.Builder(
           LogUploadWorker::class.java,
           INTERVAL_MINUTES,
           TimeUnit.MINUTES
       )
           .setConstraints(
               Constraints.Builder()
                   .setRequiredNetworkType(NetworkType.CONNECTED)
                   .build()
           ).build()
       WorkManager.getInstance(androidContext.applicationContext).enqueueUniquePeriodicWork(
           LOG_WORKNAME,
           ExistingPeriodicWorkPolicy.KEEP,
           logRequest
       )
   }
}
