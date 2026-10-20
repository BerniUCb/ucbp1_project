package org.ucb.appp1.scheduler

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase


class LogUploadWorker(
   appContext: Context,
   workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters), KoinComponent {


   private val alias: FindAliasUseCase by inject()


   override suspend fun doWork(): Result {
       println("ejecutar instrucción para subir datos")
       val response = alias.invoke("calyr")
       return Result.success()
   }
}
