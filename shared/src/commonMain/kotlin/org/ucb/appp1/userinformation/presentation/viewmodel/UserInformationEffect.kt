package org.ucb.appp1.userinformation.presentation.viewmodel

sealed interface UserInformationEffect {
    data class ShowToast(val message: String): UserInformationEffect
    object NavigateToBack: UserInformationEffect
}