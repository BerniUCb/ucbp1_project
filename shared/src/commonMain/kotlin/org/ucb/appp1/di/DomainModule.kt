package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.movies.domain.usecase.GetPopularMovies
import org.ucb.appp1.signin.domain.usecase.AuthenticateUseCase
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase

val domainModule = module {
    singleOf(::AuthenticateUseCase)
    singleOf(::FindAliasUseCase)
    singleOf(::GetPopularMovies)
}