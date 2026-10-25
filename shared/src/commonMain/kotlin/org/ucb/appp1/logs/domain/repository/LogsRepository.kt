package org.ucb.appp1.logs.domain.repository

import org.ucb.appp1.logs.domain.model.LogModel

interface LogsRepository {
    suspend fun send(log: LogModel): Boolean
}
