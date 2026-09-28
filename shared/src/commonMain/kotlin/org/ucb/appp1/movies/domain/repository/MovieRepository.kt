package org.ucb.appp1.movies.domain.repository

import org.ucb.appp1.movies.domain.model.MovieModel

interface MovieRepository {
    suspend fun popularMovies(): Result<List<MovieModel>>
}