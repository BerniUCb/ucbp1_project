package org.ucb.appp1.tasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.tasks.domain.usecase.AddTaskUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveCompletedCountUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveTasksUseCase
import org.ucb.appp1.tasks.domain.usecase.SetTaskCompletedUseCase

class TasksViewModel(
    observeTasks: ObserveTasksUseCase,
    observeCompletedCount: ObserveCompletedCountUseCase,
    private val addTask: AddTaskUseCase,
    private val setTaskCompleted: SetTaskCompletedUseCase
) : ViewModel() {

    private val error = MutableStateFlow<String?>(null)
    private val pendingIds = MutableStateFlow<Set<String>>(emptySet())

    // Si un listener falla (sin permisos, etc.) se muestra el error en vez de cerrar la app
    private val tasks = observeTasks().catch {
        println("Tasks: falló el listener de Firestore -> ${it.message}")
        showError("No se pudo leer Firestore: ${it.message}")
        emit(emptyList())
    }
    private val completedCount = observeCompletedCount().catch {
        println("Tasks: falló el listener de RTDB -> ${it.message}")
        showError("No se pudo leer Realtime DB: ${it.message}")
        emit(0)
    }

    val state: StateFlow<TasksState> = combine(
        tasks,
        completedCount,
        error,
        pendingIds
    ) { tasks, count, error, pending ->
        TasksState(isLoading = false, tasks = tasks, completedCount = count, error = error, pendingIds = pending)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS), TasksState())

    fun add(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            runCatching { addTask(title.trim()) }
                .onFailure { showError("No se pudo crear la tarea: ${it.message}") }
        }
    }

    fun toggle(id: String, completed: Boolean) {
        if (id in pendingIds.value) return
        viewModelScope.launch {
            error.update { null }
            pendingIds.update { it + id }
            runCatching { setTaskCompleted(id, completed) }
                .onFailure { showError("No se pudo actualizar, se deshizo el cambio: ${it.message}") }
            pendingIds.update { it - id }
        }
    }

    fun dismissError() = error.update { null }

    private fun showError(message: String) = error.update { message }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
