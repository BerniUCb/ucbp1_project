package org.ucb.appp1.userinformation.data.datasource

import org.ucb.appp1.userinformation.data.dto.UserInfoDto

interface GithubRemoteDataSource {
    suspend fun getUser(nickname: String): UserInfoDto
}