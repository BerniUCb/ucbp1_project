package org.ucb.appp1.movies.presentation.viewmodel

import org.ucb.appp1.movies.domain.model.MovieModel

data class MovieListState(
    val movies: List<MovieModel> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)