package org.ucb.appp1.tasks.domain.usecase

import org.ucb.appp1.tasks.domain.repository.TasksRepository

class SetTaskCompletedUseCase(private val repository: TasksRepository) {
    suspend operator fun invoke(id: String, completed: Boolean) = repository.setCompleted(id, completed)
}
