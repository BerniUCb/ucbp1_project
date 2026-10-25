package org.ucb.appp1.notifications.presentation.viewmodel

data class NotificationsState(
    val isLoading: Boolean = true,
    val token: String? = null,
    val error: String? = null
)
