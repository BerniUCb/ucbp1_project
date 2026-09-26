package org.ucb.appp1.userinformation.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val email: String?=null,
    @SerialName("avatar_url")
    val avatarUrl: String? =null,
)
