package org.ucb.appp1.articles.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.appp1.articles.data.dto.CrossrefResponseDto
import org.ucb.appp1.articles.data.mapper.toModel
import org.ucb.appp1.articles.domain.model.ArticleModel

private const val WORKS_URL = "https://api.crossref.org/works"
private const val ROWS = 10

class ArticleClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun fetchData(query: String): Result<List<ArticleModel>> {
        return try {
            val body = client.get(WORKS_URL) {
                parameter("query", query)
                parameter("rows", ROWS)
            }.body<CrossrefResponseDto>()
            Result.success(body.message.items.map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}