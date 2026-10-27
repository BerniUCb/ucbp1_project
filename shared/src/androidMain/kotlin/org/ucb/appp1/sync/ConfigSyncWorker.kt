package org.ucb.appp1.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ucb.appp1.sync.domain.usecase.SyncConfigUseCase

class ConfigSyncWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters), KoinComponent {

    private val syncConfig: SyncConfigUseCase by inject()

    // Remote Config -> Room. Si falla se reintenta; tras MAX_ATTEMPTS queda en failure
    override suspend fun doWork(): Result = runCatching { syncConfig() }.fold(
        onSuccess = {
            println("ConfigSyncWorker: sincronizado")
            Result.success()
        },
        onFailure = {
            println("ConfigSyncWorker: error -> ${it.message}")
            if (runAttemptCount < MAX_ATTEMPTS) Result.retry() else Result.failure()
        }
    )

    private companion object {
        const val MAX_ATTEMPTS = 3
    }
}
