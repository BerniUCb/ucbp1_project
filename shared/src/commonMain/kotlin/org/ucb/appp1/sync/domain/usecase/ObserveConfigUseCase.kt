package org.ucb.appp1.sync.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.sync.domain.model.ConfigModel
import org.ucb.appp1.sync.domain.repository.SyncRepository

class ObserveConfigUseCase(private val repository: SyncRepository) {
    operator fun invoke(): Flow<ConfigModel> = repository.observeConfig()
}
