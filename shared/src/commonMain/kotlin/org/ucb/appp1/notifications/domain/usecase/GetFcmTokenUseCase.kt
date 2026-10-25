package org.ucb.appp1.notifications.domain.usecase

import org.ucb.appp1.notifications.domain.repository.NotificationsRepository

class GetFcmTokenUseCase(
    val repository: NotificationsRepository
) {
    suspend fun invoke(): String {
        return repository.getToken()
    }
}
