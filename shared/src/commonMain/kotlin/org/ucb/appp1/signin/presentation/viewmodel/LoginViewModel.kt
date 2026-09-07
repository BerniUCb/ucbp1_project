package org.ucb.appp1.signin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.signin.domain.model.Email
import org.ucb.appp1.signin.domain.model.Password
import org.ucb.appp1.signin.domain.usecase.AuthenticateUseCase

class LoginViewModel(
    val authenticateUseCase: AuthenticateUseCase
): ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<LoginEffects>()
    val effects = _effects.asSharedFlow()

    private fun emitEffect(effect: LoginEffects) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    fun emitEvent(event: LoginEvents) {
        when(event) {
            is LoginEvents.OnEmailChanged -> {
                _state.update { it.copy(email = event.value) }
            }
            is LoginEvents.OnPasswordChanged -> {

            }
            LoginEvents.OnSubmit -> {
                var isValid = true
                if (state.value.email.isBlank()) {
                    emitEffect(LoginEffects.ShowToast("The email field is required"))
                    isValid = false
                } else if (state.value.password.isBlank()) {
                    emitEffect(LoginEffects.ShowToast("The password field is required"))
                }
                if (isValid) {
                    viewModelScope.launch {
                        authenticateUseCase.invoke(Email(state.value.email), Password(state.value.password))
                            .fold(
                                ifLeft = {
                                    emitEffect(LoginEffects.ShowToast("Credential Invalid"))
                                },
                                ifRight = {
                                    emitEffect(LoginEffects.NavigateToHome)
                                }
                            )
                    }
                }
            }
        }
    }
}
