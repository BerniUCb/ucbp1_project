package org.ucb.appp1.logs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.time.Clock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ucb.appp1.logs.domain.model.LogLevelModel
import org.ucb.appp1.logs.domain.model.LogModel
import org.ucb.appp1.logs.domain.usecase.SendLogUseCase

class LogsViewModel(
    val usecase: SendLogUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LogsState())
    val state = _state.asStateFlow()

    // Ejemplo de llamada: arma un log de prueba y lo envía por gRPC
    fun sendTestLog() {
        viewModelScope.launch {
            _state.value = LogsState(isSending = true)
            val log = LogModel(
                level = LogLevelModel.INFO,
                message = "Log de prueba desde la app",
                androidId = "android-id-demo",
                appInstanceId = "app-instance-demo",
                versionCode = 1,
                userId = "bernardo",
                mobileTimeStamp = Clock.System.now().toEpochMilliseconds()
            )
            _state.value = runCatching { usecase.invoke(log) }.fold(
                onSuccess = { ok ->
                    LogsState(result = if (ok) "LOG_RESULT_OK" else "LOG_RESULT_ERROR")
                },
                onFailure = {
                    println("gRPC logs error: ${it.message}")
                    LogsState(error = it.message ?: "Error al enviar el log")
                }
            )
        }
    }
}
