package org.ucb.appp1.logs.domain.usecase

import org.ucb.appp1.logs.domain.model.LogModel
import org.ucb.appp1.logs.domain.repository.LogsRepository

class SendLogUseCase(
    val repository: LogsRepository
) {
    suspend fun invoke(log: LogModel): Boolean {
        return repository.send(log)
    }
}
