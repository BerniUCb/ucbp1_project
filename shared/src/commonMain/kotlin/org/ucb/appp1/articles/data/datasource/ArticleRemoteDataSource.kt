package org.ucb.appp1.articles.data.datasource

import org.ucb.appp1.articles.data.service.ArticleClient
import org.ucb.appp1.articles.domain.model.ArticleModel

class ArticleRemoteDataSource(val service: ArticleClient) {
    suspend fun getList(query: String): Result<List<ArticleModel>> {
        return service.fetchData(query)
    }
}