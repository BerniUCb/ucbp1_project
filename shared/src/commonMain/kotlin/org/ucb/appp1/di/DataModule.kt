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
import org.ucb.appp1.stores.data.datasource.FirestoreDataSource
import org.ucb.appp1.stores.data.repository.StoresRepositoryImpl
import org.ucb.appp1.stores.domain.repository.StoresRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository
import org.ucb.appp1.contact.data.datasource.RemoteConfigDataSource
import org.ucb.appp1.contact.data.repository.ContactRepositoryImpl
import org.ucb.appp1.contact.domain.repository.ContactRepository
import org.ucb.appp1.logs.data.datasource.LogsRemoteDataSource
import org.ucb.appp1.logs.data.repository.LogsRepositoryImpl
import org.ucb.appp1.logs.domain.repository.LogsRepository
import org.ucb.appp1.notifications.data.datasource.MessagingDataSource
import org.ucb.appp1.notifications.data.repository.NotificationsRepositoryImpl
import org.ucb.appp1.notifications.domain.repository.NotificationsRepository

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
    singleOf(::FirestoreDataSource)
    single<StoresRepository> { StoresRepositoryImpl(get()) }
    single<RemoteConfigDataSource> { RemoteConfigDataSource() }
    single<ContactRepository> { ContactRepositoryImpl(get()) }
    single { LogsRemoteDataSource(get()) }
    single<LogsRepository> { LogsRepositoryImpl(get()) }
    singleOf(::MessagingDataSource)
    single<NotificationsRepository> { NotificationsRepositoryImpl(get()) }
}
