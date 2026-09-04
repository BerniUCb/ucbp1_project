package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.signin.domain.usecase.AuthenticateUseCase

val domainModule = module {
    singleOf(::AuthenticateUseCase)
}