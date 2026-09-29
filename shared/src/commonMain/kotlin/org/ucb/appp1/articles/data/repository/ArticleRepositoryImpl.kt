package org.ucb.appp1.articles.data.repository

import org.ucb.appp1.articles.data.datasource.ArticleRemoteDataSource
import org.ucb.appp1.articles.domain.model.ArticleModel
import org.ucb.appp1.articles.domain.repository.ArticleRepository

class ArticleRepositoryImpl(
    val datasource: ArticleRemoteDataSource
) : ArticleRepository {
    override suspend fun searchArticles(query: String): Result<List<ArticleModel>> {
        return datasource.getList(query)
    }
}