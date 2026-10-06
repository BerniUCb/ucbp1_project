package org.ucb.appp1.exchange.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.exchange.domain.repository.ExchangeRepository

class ObserveExchangeUseCase(
    val repository: ExchangeRepository
) {
    suspend fun invoke(): Flow<String?> {
        return repository.observe()
    }
}
