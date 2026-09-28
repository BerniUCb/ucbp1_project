package org.ucb.appp1.movies.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    val page: Int,
    val results: List<MovieDto>
)

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null
)