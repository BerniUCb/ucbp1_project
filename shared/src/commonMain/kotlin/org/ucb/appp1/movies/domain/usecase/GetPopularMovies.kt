package org.ucb.appp1.movies.domain.usecase

import org.ucb.appp1.movies.domain.model.MovieModel
import org.ucb.appp1.movies.domain.repository.MovieRepository

class GetPopularMovies(
    private val repository: MovieRepository
) {

    suspend fun invoke(): List<MovieModel> {
        return repository.getMovies()
    }
}