package org.ucb.appp1.articles.domain.repository

import org.ucb.appp1.articles.domain.model.ArticleModel

interface ArticleRepository {
    suspend fun searchArticles(query: String): Result<List<ArticleModel>>
}