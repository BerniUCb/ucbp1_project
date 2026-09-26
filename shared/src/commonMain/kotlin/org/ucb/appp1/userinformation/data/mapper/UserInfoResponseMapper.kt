package org.ucb.appp1.userinformation.data.mapper

import org.ucb.appp1.userinformation.data.dto.UserInfoDto
import org.ucb.appp1.userinformation.domain.model.UserInfoModel

fun UserInfoDto.toDomain(): UserInfoModel = UserInfoModel(
    email = email?:"",
    company = "",
    avatarUrl = avatarUrl?:"",
    alias = ""
)