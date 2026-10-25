package org.ucb.appp1.logs.domain.model

enum class LogLevelModel { INFO, DEBUG, TRACE, WARN, ERROR, FATAL }

data class LogModel(
    val level: LogLevelModel,
    val message: String,
    val stackTrace: String = "",
    val androidId: String,
    val appInstanceId: String,
    val versionCode: Int,
    val userId: String,
    val mobileTimeStamp: Long
)
