package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.config.AppDatabase
import org.ucb.appp1.exchange.data.datasource.RealTimeDataBase
import org.ucb.appp1.exchange.data.repository.ExchangeRepositoryImpl
import org.ucb.appp1.exchange.domain.repository.ExchangeRepository
import org.ucb.appp1.movies.data.dao.DollarDao
import org.ucb.appp1.movies.data.datasource.DollarLocalDataSource
import org.ucb.appp1.movies.data.repository.DollarRepositoryImpl
import org.ucb.appp1.movies.domain.repository.DollarRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository
import org.ucb.appp1.contact.data.datasource.RemoteConfigDataSource
import org.ucb.appp1.contact.data.repository.ContactRepositoryImpl
import org.ucb.appp1.contact.domain.repository.ContactRepository

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository>{GithubRepositoryImpl(get())}
    single<DollarDao> {
        get<AppDatabase>().getDao()
    }
    singleOf(::DollarLocalDataSource)
    single<DollarRepository> { DollarRepositoryImpl(get()) }
    singleOf(::RealTimeDataBase)
    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }
    singleOf(::RemoteConfigDataSource)
    single<ContactRepository> { ContactRepositoryImpl(get()) }
}
