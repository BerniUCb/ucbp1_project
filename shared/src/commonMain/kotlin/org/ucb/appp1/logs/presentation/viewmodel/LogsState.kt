package org.ucb.appp1.logs.presentation.viewmodel

data class LogsState(
    val isSending: Boolean = false,
    val result: String? = null,
    val error: String? = null
)
