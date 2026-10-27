package org.ucb.appp1.tasks.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.domain.model.TaskModel

interface TasksRepository {
    fun observeTasks(): Flow<List<TaskModel>>
    fun observeCompletedCount(): Flow<Int>
    suspend fun addTask(title: String)
    suspend fun setCompleted(id: String, completed: Boolean)
}
