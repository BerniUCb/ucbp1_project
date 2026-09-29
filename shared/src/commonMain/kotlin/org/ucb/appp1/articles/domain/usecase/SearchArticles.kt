package org.ucb.appp1.articles.domain.usecase

import org.ucb.appp1.articles.domain.model.ArticleModel
import org.ucb.appp1.articles.domain.repository.ArticleRepository

class SearchArticles(
    private val repository: ArticleRepository
) {
    suspend fun invoke(query: String): Result<List<ArticleModel>> {
        return repository.searchArticles(query)
    }
}