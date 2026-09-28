package org.ucb.appp1.movies.domain.model

import org.ucb.appp1.movies.domain.vo.PosterPath

data class MovieModel(
    val id: String,
    val title: String,
    val description: String,
    val posterPath: PosterPath
)