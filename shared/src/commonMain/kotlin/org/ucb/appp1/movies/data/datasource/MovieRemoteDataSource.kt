package org.ucb.appp1.movies.data.datasource

import org.ucb.appp1.movies.data.service.MovieClient
import org.ucb.appp1.movies.domain.model.MovieModel

class MovieRemoteDataSource(val service: MovieClient) {
    suspend fun getList(): Result<List<MovieModel>> {
        return service.fetchData()
    }
}