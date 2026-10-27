package org.ucb.appp1.tasks.data.dto

import kotlinx.serialization.Serializable
import org.ucb.appp1.tasks.domain.model.TaskModel

@Serializable
data class TaskDto(
    val title: String = "",
    val completed: Boolean = false
) {
    fun toModel(id: String) = TaskModel(id = id, title = title, completed = completed)
}
