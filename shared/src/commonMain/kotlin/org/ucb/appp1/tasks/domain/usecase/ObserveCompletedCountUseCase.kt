package org.ucb.appp1.tasks.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.domain.repository.TasksRepository

class ObserveCompletedCountUseCase(private val repository: TasksRepository) {
    operator fun invoke(): Flow<Int> = repository.observeCompletedCount()
}
