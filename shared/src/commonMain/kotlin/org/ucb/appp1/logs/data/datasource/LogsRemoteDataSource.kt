package org.ucb.appp1.logs.data.datasource

import com.develoop.logs.LogRequest
import com.develoop.logs.LogResponse
import com.develoop.logs.LogServiceClient

class LogsRemoteDataSource(
    private val service: LogServiceClient
) {
    suspend fun send(request: LogRequest): LogResponse {
        return service.send().execute(request)
    }
}
