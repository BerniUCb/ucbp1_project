package org.ucb.appp1.userinformation.data.repository

import org.ucb.appp1.userinformation.domain.model.UserInfoModel
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

class GithubRepositoryImpl: GithubRepository {
    override suspend fun findByAlias(alias: String): Result<UserInfoModel> {
        return Result.success(UserInfoModel(
            email = "calyr.software@gmail.com",
            company = "develoop.net",
            avatarUrl = "https://avatars.githubusercontent.com/u/874321?v=4",
            alias = alias
        ))
    }
}