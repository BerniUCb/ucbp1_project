package org.ucb.appp1.notifications.domain.repository

interface NotificationsRepository {
    suspend fun getToken(): String
}
