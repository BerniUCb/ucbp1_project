package org.ucb.appp1.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.appp1.articles.presentation.viewmodel.ArticleListViewModel
import org.ucb.appp1.movies.presentation.viewmodel.MovieListViewModel
import org.ucb.appp1.signin.presentation.viewmodel.LoginViewModel
import org.ucb.appp1.userinformation.presentation.viewmodel.UserInformationViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::UserInformationViewModel)
    viewModelOf(::MovieListViewModel)
    viewModelOf(::ArticleListViewModel)
}