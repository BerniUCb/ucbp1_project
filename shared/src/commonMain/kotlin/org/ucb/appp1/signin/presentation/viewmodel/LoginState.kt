package org.ucb.appp1.signin.presentation.viewmodel

data class LoginState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoading : Boolean = false
)
