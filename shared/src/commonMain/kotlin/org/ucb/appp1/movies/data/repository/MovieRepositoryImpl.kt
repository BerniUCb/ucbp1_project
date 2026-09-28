package org.ucb.appp1.movies.data.repository

import org.ucb.appp1.movies.data.datasource.MovieRemoteDataSource
import org.ucb.appp1.movies.domain.model.MovieModel
import org.ucb.appp1.movies.domain.repository.MovieRepository

class MovieRepositoryImpl(
    val datasource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun popularMovies(): Result<List<MovieModel>> {
        return datasource.getList()
    }
}