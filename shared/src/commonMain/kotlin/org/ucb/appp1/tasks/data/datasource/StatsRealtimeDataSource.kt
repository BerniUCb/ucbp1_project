package org.ucb.appp1.tasks.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer

private const val PATH_COMPLETED = "stats/completadas"

class StatsRealtimeDataSource {

    private val completedRef by lazy { Firebase.database.reference(PATH_COMPLETED) }

    // Equivalente a ValueEventListener: emite cada vez que cambia /stats/completadas
    fun observeCompleted(): Flow<Int> = completedRef.valueEvents.map { snapshot ->
        snapshot.value<Int?>() ?: 0
    }

    // Transacción: suma o resta sin pisar cambios de otros dispositivos y nunca baja de 0
    suspend fun addToCompleted(delta: Int) {
        completedRef.runTransaction(Int.serializer().nullable) { current ->
            ((current ?: 0) + delta).coerceAtLeast(0)
        }
    }
}
