package org.ucb.appp1.tasks.presentation.viewmodel

import org.ucb.appp1.tasks.domain.model.TaskModel

data class TasksState(
    // true hasta que llegan los primeros datos de Firestore y RTDB
    val isLoading: Boolean = true,
    val tasks: List<TaskModel> = emptyList(),
    val completedCount: Int = 0,
    val error: String? = null,
    // Tareas con una escritura en curso: su checkbox se bloquea para evitar dobles toques
    val pendingIds: Set<String> = emptySet()
)
