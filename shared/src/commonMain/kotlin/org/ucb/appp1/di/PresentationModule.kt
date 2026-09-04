package org.ucb.appp1.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.appp1.signin.presentation.viewmodel.LoginViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
}
