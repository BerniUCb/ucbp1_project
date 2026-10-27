package org.ucb.appp1.tasks.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.domain.model.TaskModel
import org.ucb.appp1.tasks.domain.repository.TasksRepository

class ObserveTasksUseCase(private val repository: TasksRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = repository.observeTasks()
}
