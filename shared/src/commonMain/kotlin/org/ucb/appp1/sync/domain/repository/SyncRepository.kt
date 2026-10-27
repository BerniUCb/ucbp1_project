package org.ucb.appp1.sync.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.sync.domain.model.ConfigModel

interface SyncRepository {
    fun observeConfig(): Flow<ConfigModel>
    suspend fun syncFromRemote()
}
