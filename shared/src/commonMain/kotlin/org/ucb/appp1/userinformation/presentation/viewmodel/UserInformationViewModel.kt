package org.ucb.appp1.userinformation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase

class UserInformationViewModel(
    val findAliasUseCase: FindAliasUseCase
): ViewModel() {
    private val _state = MutableStateFlow<UserInformationState>(UserInformationState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserInformationEffect>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: UserInformationEvent) {
        when( event) {
            is UserInformationEvent.OnAliasChange -> {
                _state.update { it.copy(alias = event.value) }
            }
            UserInformationEvent.OnBack -> {
                emitEffect(UserInformationEffect.NavigateToBack)
            }
            UserInformationEvent.OnSubmit -> {
                viewModelScope.launch {
                    findAliasUseCase.invoke(_state.value.alias).fold(
                        onSuccess = { userInfo ->
                            _state.update {
                                it.copy(email = userInfo.email, avatarUrl = userInfo.avatarUrl)
                            }
                        },
                        onFailure = {

                        }
                    )
                }

            }
        }
    }

    private fun emitEffect(effect: UserInformationEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}