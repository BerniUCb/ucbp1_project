package org.ucb.appp1.di

import org.koin.dsl.module
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single<GithubRepository>{GithubRepositoryImpl()}
}
