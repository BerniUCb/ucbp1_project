package org.ucb.appp1.sync.domain.scheduler

import kotlinx.coroutines.flow.Flow

enum class SyncStatus {
    /** El Worker está corriendo ahora mismo */
    SYNCING,

    /** La última sincronización terminó bien */
    SYNCED,

    /** Sin sincronizar: sin red, fallida o todavía en cola */
    STALE
}

interface SyncScheduler {
    /** Encola la sincronización al abrir la app (no duplica si ya hay una en cola) */
    fun scheduleInitialSync()

    /** Dispara el Worker a mano (pull-to-refresh) */
    fun syncNow()

    val status: Flow<SyncStatus>
}
