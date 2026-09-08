package org.ucb.appp1.userinformation.presentation.viewmodel

data class UserInformationState(
    val alias: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String? = null,
    val company: String? = null,
    val avatarUrl: String? = null
)