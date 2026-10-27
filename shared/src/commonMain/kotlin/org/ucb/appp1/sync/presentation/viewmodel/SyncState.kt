package org.ucb.appp1.sync.presentation.viewmodel

import org.ucb.appp1.sync.domain.model.ConfigModel
import org.ucb.appp1.sync.domain.scheduler.SyncStatus

data class SyncState(
    val config: ConfigModel = ConfigModel(),
    val status: SyncStatus = SyncStatus.STALE
) {
    val isRefreshing: Boolean get() = status == SyncStatus.SYNCING

    // Hay datos en Room pero la última sincronización no se completó
    val isCached: Boolean
        get() = status == SyncStatus.STALE &&
            (config.features.isNotEmpty() || config.banners.isNotEmpty())
}
