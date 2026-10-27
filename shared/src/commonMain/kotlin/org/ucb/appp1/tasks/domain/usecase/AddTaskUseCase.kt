package org.ucb.appp1.tasks.domain.usecase

import org.ucb.appp1.tasks.domain.repository.TasksRepository

class AddTaskUseCase(private val repository: TasksRepository) {
    suspend operator fun invoke(title: String) = repository.addTask(title)
}
