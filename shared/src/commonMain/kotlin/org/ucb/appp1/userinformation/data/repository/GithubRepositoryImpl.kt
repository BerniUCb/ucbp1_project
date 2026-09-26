package org.ucb.appp1.userinformation.data.repository

import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.mapper.toDomain
import org.ucb.appp1.userinformation.domain.model.UserInfoModel
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

class GithubRepositoryImpl(val dataSource: GithubRemoteDataSource): GithubRepository {
    override suspend fun findByAlias(alias: String): Result<UserInfoModel> {
        return Result.success(dataSource.getUser(alias).toDomain())
    }
}