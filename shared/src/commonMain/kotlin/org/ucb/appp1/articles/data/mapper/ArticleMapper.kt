package org.ucb.appp1.articles.data.mapper

import org.ucb.appp1.articles.data.dto.ArticleDto
import org.ucb.appp1.articles.data.dto.AuthorDto
import org.ucb.appp1.articles.data.dto.PublishedDto
import org.ucb.appp1.articles.domain.model.ArticleModel

fun ArticleDto.toModel(): ArticleModel = ArticleModel(
    title = title.firstOrNull() ?: "Sin título",
    authors = author.map { it.toDisplayName() }.filter { it.isNotBlank() },
    published = published.toDisplayDate(),
    journal = containerTitle.firstOrNull() ?: "",
    doi = doi,
    type = type,
    url = url
)

private fun AuthorDto.toDisplayName(): String =
    listOfNotNull(given, family).joinToString(" ").ifBlank { name ?: "" }

private fun PublishedDto?.toDisplayDate(): String {
    val parts = this?.dateParts?.firstOrNull()?.filterNotNull() ?: return ""
    return parts.joinToString("-") { it.toString().padStart(2, '0') }
}