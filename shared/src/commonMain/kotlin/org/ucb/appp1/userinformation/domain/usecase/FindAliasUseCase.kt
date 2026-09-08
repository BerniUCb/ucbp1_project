package org.ucb.appp1.userinformation.domain.usecase

import org.ucb.appp1.userinformation.domain.model.UserInfoModel
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

class FindAliasUseCase(
    val repository: GithubRepository
) {
    suspend fun invoke(alias: String): Result<UserInfoModel> {
        return repository.findByAlias(alias)
    }
}