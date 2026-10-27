package org.ucb.appp1.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ucb.appp1.sync.domain.scheduler.SyncScheduler
import org.ucb.appp1.sync.domain.scheduler.SyncStatus

class WorkManagerSyncScheduler(context: Context) : SyncScheduler {

    private val workManager = WorkManager.getInstance(context.applicationContext)

    override fun scheduleInitialSync() = enqueue(ExistingWorkPolicy.KEEP)

    override fun syncNow() = enqueue(ExistingWorkPolicy.REPLACE)

    override val status: Flow<SyncStatus> =
        workManager.getWorkInfosForUniqueWorkFlow(WORK_NAME).map { infos ->
            val states = infos.map { it.state }.filter { it != WorkInfo.State.CANCELLED }
            when {
                WorkInfo.State.RUNNING in states -> SyncStatus.SYNCING
                states.isNotEmpty() && states.all { it == WorkInfo.State.SUCCEEDED } -> SyncStatus.SYNCED
                else -> SyncStatus.STALE
            }
        }

    private fun enqueue(policy: ExistingWorkPolicy) {
        val request = OneTimeWorkRequestBuilder<ConfigSyncWorker>()
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .build()
        workManager.enqueueUniqueWork(WORK_NAME, policy, request)
    }

    private companion object {
        const val WORK_NAME = "configSyncWork"
    }
}
