package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.exchange.domain.usecase.ObserveExchangeUseCase
import org.ucb.appp1.signin.domain.usecase.AuthenticateUseCase
import org.ucb.appp1.stores.domain.usecase.GetStoresUseCase
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase
import org.ucb.appp1.contact.domain.usecase.GetContactUseCase
import org.ucb.appp1.logs.domain.usecase.SendLogUseCase

val domainModule = module {
    singleOf(::AuthenticateUseCase)
    singleOf(::FindAliasUseCase)
    singleOf(::ObserveExchangeUseCase)
    singleOf(::GetStoresUseCase)
    singleOf(::GetContactUseCase)
    singleOf(::SendLogUseCase)
}
