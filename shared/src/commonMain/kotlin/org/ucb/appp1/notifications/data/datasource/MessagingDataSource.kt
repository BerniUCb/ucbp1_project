package org.ucb.appp1.notifications.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.messaging.messaging

class MessagingDataSource {

    private val messaging by lazy { Firebase.messaging }

    suspend fun getToken(): String {
        return messaging.getToken()
    }
}
