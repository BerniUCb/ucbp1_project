package org.ucb.appp1.di

import org.koin.dsl.module
import org.ucb.appp1.articles.data.datasource.ArticleRemoteDataSource
import org.ucb.appp1.articles.data.repository.ArticleRepositoryImpl
import org.ucb.appp1.articles.data.service.ArticleClient
import org.ucb.appp1.articles.domain.repository.ArticleRepository
import org.ucb.appp1.movies.data.datasource.MovieRemoteDataSource
import org.ucb.appp1.movies.data.repository.MovieRepositoryImpl
import org.ucb.appp1.movies.data.service.MovieClient
import org.ucb.appp1.movies.domain.repository.MovieRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }

    single { MovieClient() }
    single { MovieRemoteDataSource(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single { ArticleClient() }
    single { ArticleRemoteDataSource(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
}