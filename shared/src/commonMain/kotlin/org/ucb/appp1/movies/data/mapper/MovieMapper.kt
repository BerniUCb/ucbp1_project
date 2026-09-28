package org.ucb.appp1.movies.data.mapper
import org.ucb.appp1.movies.data.dto.MovieDto
import org.ucb.appp1.movies.domain.model.MovieModel
import org.ucb.appp1.movies.domain.vo.PosterPath

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toModel(): MovieModel = MovieModel(
    id = id.toString(),
    title = title,
    description = overview ?: "",
    posterPath = PosterPath(if (posterPath != null) IMAGE_BASE_URL + posterPath else "")
)
