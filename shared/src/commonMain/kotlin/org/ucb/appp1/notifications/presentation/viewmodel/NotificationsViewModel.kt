package org.ucb.appp1.notifications.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ucb.appp1.notifications.domain.usecase.GetFcmTokenUseCase

class NotificationsViewModel(
    val usecase: GetFcmTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NotificationsState())
    val state = _state.asStateFlow()

    init {
        loadToken()
    }

    private fun loadToken() {
        viewModelScope.launch {
            _state.value = runCatching { usecase.invoke() }.fold(
                onSuccess = {
                    println("FCM token = $it")
                    NotificationsState(isLoading = false, token = it)
                },
                onFailure = {
                    println("FCM error: ${it.message}")
                    NotificationsState(isLoading = false, error = it.message ?: "Error al obtener el token")
                }
            )
        }
    }
}
