package org.ucb.appp1.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.appp1.exchange.presentation.viewmodel.ExchangeViewModel
import org.ucb.appp1.movies.presentation.viewmodel.DollarViewModel
import org.ucb.appp1.signin.presentation.viewmodel.LoginViewModel
import org.ucb.appp1.stores.presentation.viewmodel.StoresViewModel
import org.ucb.appp1.userinformation.presentation.viewmodel.UserInformationViewModel
import org.ucb.appp1.contact.presentation.viewmodel.ContactViewModel
import org.ucb.appp1.logs.presentation.viewmodel.LogsViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::UserInformationViewModel)
    viewModelOf(::DollarViewModel)
    viewModelOf(::ExchangeViewModel)
    viewModelOf(::StoresViewModel)
    viewModelOf(::ContactViewModel)
    viewModelOf(::LogsViewModel)
}
