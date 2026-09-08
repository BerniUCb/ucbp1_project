package org.ucb.appp1.userinformation.presentation.viewmodel

sealed interface UserInformationEvent {
    object OnBack : UserInformationEvent
    object OnSubmit: UserInformationEvent
    data class OnAliasChange(val value: String): UserInformationEvent
}