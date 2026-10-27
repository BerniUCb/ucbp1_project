package org.ucb.appp1.tasks.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ucb.appp1.tasks.data.dto.TaskDto
import org.ucb.appp1.tasks.domain.model.TaskModel

private const val COLLECTION_TASKS = "tareas"
private const val FIELD_COMPLETED = "completed"

class TasksFirestoreDataSource {

    private val firestore by lazy { Firebase.firestore }
    private val tasksRef by lazy { firestore.collection(COLLECTION_TASKS) }

    // Equivalente a addSnapshotListener: emite cada vez que cambia la colección
    fun observeTasks(): Flow<List<TaskModel>> = tasksRef.snapshots.map { snapshot ->
        snapshot.documents
            .map { it.data<TaskDto>().toModel(it.id) }
            .sortedBy { it.title }
    }

    suspend fun addTask(title: String) {
        tasksRef.add(TaskDto(title = title, completed = false))
    }

    /**
     * Transacción: lee el valor actual y solo escribe si es distinto.
     * Devuelve true si cambió; false si ya tenía ese valor (otro dispositivo se adelantó o doble toque),
     * así el contador de RTDB no suma dos veces la misma tarea.
     */
    suspend fun setCompleted(id: String, completed: Boolean): Boolean = firestore.runTransaction {
        val taskRef = tasksRef.document(id)
        val current = get(taskRef).get<Boolean?>(FIELD_COMPLETED) ?: false
        if (current == completed) {
            false
        } else {
            update(taskRef, FIELD_COMPLETED to completed)
            true
        }
    }
}
