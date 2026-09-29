package org.ucb.appp1.articles.domain.model

data class ArticleModel(
    val title: String,
    val authors: List<String>,
    val published: String,
    val journal: String,
    val doi: String,
    val type: String,
    val url: String
)