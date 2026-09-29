package org.ucb.appp1.articles.presentation.viewmodel

import org.ucb.appp1.articles.domain.model.ArticleModel

data class ArticleListState(

    val query: String = "machine learning",
    val articles: List<ArticleModel> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)