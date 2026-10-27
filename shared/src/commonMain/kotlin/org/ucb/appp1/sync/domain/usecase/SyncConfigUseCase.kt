package org.ucb.appp1.sync.domain.usecase

import org.ucb.appp1.sync.domain.repository.SyncRepository

class SyncConfigUseCase(private val repository: SyncRepository) {
    suspend operator fun invoke() = repository.syncFromRemote()
}
