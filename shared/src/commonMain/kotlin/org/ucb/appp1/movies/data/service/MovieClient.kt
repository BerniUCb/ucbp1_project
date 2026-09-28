package org.ucb.appp1.movies.data.service
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.appp1.movies.data.dto.MovieResponseDto
import org.ucb.appp1.movies.data.mapper.toModel
import org.ucb.appp1.movies.domain.model.MovieModel

private const val API_KEY = "fa3e844ce31744388e07fa47c7c5d8c3"
private const val POPULAR_URL =
    "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=$API_KEY"

class MovieClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun fetchData(): Result<List<MovieModel>> {
        return try {
            val body = client.get(POPULAR_URL).body<MovieResponseDto>()
            Result.success(body.results.map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
