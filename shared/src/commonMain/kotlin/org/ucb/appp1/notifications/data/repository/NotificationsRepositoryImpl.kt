package org.ucb.appp1.notifications.data.repository

import org.ucb.appp1.notifications.data.datasource.MessagingDataSource
import org.ucb.appp1.notifications.domain.repository.NotificationsRepository

class NotificationsRepositoryImpl(
    val messagingDataSource: MessagingDataSource
) : NotificationsRepository {
    override suspend fun getToken(): String {
        return messagingDataSource.getToken()
    }
}
