package org.ucb.appp1.tasks.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import org.ucb.appp1.tasks.data.datasource.StatsRealtimeDataSource
import org.ucb.appp1.tasks.data.datasource.TasksFirestoreDataSource
import org.ucb.appp1.tasks.domain.model.TaskModel
import org.ucb.appp1.tasks.domain.repository.TasksRepository

private const val WRITE_TIMEOUT_MS = 10_000L

class TasksRepositoryImpl(
    private val firestore: TasksFirestoreDataSource,
    private val stats: StatsRealtimeDataSource
) : TasksRepository {

    override fun observeTasks(): Flow<List<TaskModel>> = firestore.observeTasks()

    override fun observeCompletedCount(): Flow<Int> = stats.observeCompleted()

    override suspend fun addTask(title: String) = withTimeout(WRITE_TIMEOUT_MS) { firestore.addTask(title) }

    /**
     * Firestore y Realtime DB no comparten transacción, así que se hace en dos pasos con
     * rollback lógico:
     *  1. Firestore (transacción): marcar/desmarcar la tarea, solo si de verdad cambia.
     *  2. RTDB (transacción): sumar/restar 1 al contador.
     * Si el paso 2 falla, se deshace el paso 1 para que las dos bases queden iguales.
     * Si el paso 1 falla no se escribió nada, así que no hay nada que deshacer.
     */
    override suspend fun setCompleted(id: String, completed: Boolean) {
        val changed = runCatching { withTimeout(WRITE_TIMEOUT_MS) { firestore.setCompleted(id, completed) } }
            .onFailure { println("Tasks: falló Firestore, no se escribió nada -> ${it.message}") }
            .getOrThrow()
        if (!changed) {
            println("Tasks: la tarea $id ya estaba en completed=$completed, el contador no se toca")
            return
        }

        runCatching { withTimeout(WRITE_TIMEOUT_MS) { stats.addToCompleted(if (completed) 1 else -1) } }
            .onFailure { statsError ->
                println("Tasks: falló RTDB, haciendo rollback en Firestore -> ${statsError.message}")
                runCatching { withTimeout(WRITE_TIMEOUT_MS) { firestore.setCompleted(id, !completed) } }
                    .onSuccess { println("Tasks: rollback correcto, las dos bases quedaron como antes") }
                    .onFailure { println("Tasks: ROLLBACK FALLÓ, bases inconsistentes -> ${it.message}") }
                throw statsError
            }
    }
}
