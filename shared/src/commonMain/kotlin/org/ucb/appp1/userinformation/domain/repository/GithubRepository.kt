package org.ucb.appp1.userinformation.domain.repository

import org.ucb.appp1.userinformation.domain.model.UserInfoModel

interface GithubRepository {
    suspend fun findByAlias(alias: String): Result<UserInfoModel>
}