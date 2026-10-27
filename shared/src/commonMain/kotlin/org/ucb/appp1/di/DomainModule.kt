package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.exchange.domain.usecase.ObserveExchangeUseCase
import org.ucb.appp1.signin.domain.usecase.AuthenticateUseCase
import org.ucb.appp1.stores.domain.usecase.GetStoresUseCase
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase
import org.ucb.appp1.contact.domain.usecase.GetContactUseCase
import org.ucb.appp1.logs.domain.usecase.SendLogUseCase
import org.ucb.appp1.notifications.domain.usecase.GetFcmTokenUseCase
import org.ucb.appp1.sync.domain.usecase.ObserveConfigUseCase
import org.ucb.appp1.sync.domain.usecase.SyncConfigUseCase
import org.ucb.appp1.tasks.domain.usecase.AddTaskUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveCompletedCountUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveTasksUseCase
import org.ucb.appp1.tasks.domain.usecase.SetTaskCompletedUseCase

val domainModule = module {
    singleOf(::AuthenticateUseCase)
    singleOf(::FindAliasUseCase)
    singleOf(::ObserveExchangeUseCase)
    singleOf(::GetStoresUseCase)
    singleOf(::GetContactUseCase)
    singleOf(::SendLogUseCase)
    singleOf(::GetFcmTokenUseCase)
    singleOf(::ObserveConfigUseCase)
    singleOf(::SyncConfigUseCase)
    singleOf(::ObserveTasksUseCase)
    singleOf(::ObserveCompletedCountUseCase)
    singleOf(::AddTaskUseCase)
    singleOf(::SetTaskCompletedUseCase)
}
