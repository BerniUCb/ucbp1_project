package org.ucb.appp1.signin.presentation.viewmodel

sealed interface LoginEffects {
    object NavigateToHome: LoginEffects
    object SignUp: LoginEffects
    data class ShowToast(val message: String): LoginEffects
}
