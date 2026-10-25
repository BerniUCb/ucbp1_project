package org.ucb.appp1.logs.data.repository

import com.develoop.logs.ELogLevel
import com.develoop.logs.ELogResultCode
import com.develoop.logs.LogData
import com.develoop.logs.LogRequest
import okio.ByteString.Companion.encodeUtf8
import org.ucb.appp1.logs.data.datasource.LogsRemoteDataSource
import org.ucb.appp1.logs.domain.model.LogLevelModel
import org.ucb.appp1.logs.domain.model.LogModel
import org.ucb.appp1.logs.domain.repository.LogsRepository

class LogsRepositoryImpl(
    val remoteDataSource: LogsRemoteDataSource
) : LogsRepository {
    override suspend fun send(log: LogModel): Boolean {
        val data = LogData(
            androidId = log.androidId.encodeUtf8(),
            appInstanceId = log.appInstanceId.encodeUtf8(),
            logLevel = log.level.toProto(),
            message = log.message,
            stackTrace = log.stackTrace,
            mobileTimeStamp = log.mobileTimeStamp,
            versionCode = log.versionCode,
            userId = log.userId
        )
        val response = remoteDataSource.send(LogRequest(logs = listOf(data)))
        println("gRPC logs resultCode = ${response.resultCode}")
        return response.resultCode == ELogResultCode.LOG_RESULT_OK
    }
}

private fun LogLevelModel.toProto(): ELogLevel = when (this) {
    LogLevelModel.INFO -> ELogLevel.LEVEL_INFO
    LogLevelModel.DEBUG -> ELogLevel.LEVEL_DEBUG
    LogLevelModel.TRACE -> ELogLevel.LEVEL_TRACE
    LogLevelModel.WARN -> ELogLevel.LEVEL_WARN
    LogLevelModel.ERROR -> ELogLevel.LEVEL_ERROR
    LogLevelModel.FATAL -> ELogLevel.LEVEL_FATAL
}
