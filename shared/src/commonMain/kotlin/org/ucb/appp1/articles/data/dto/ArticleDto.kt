package org.ucb.appp1.articles.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrossrefResponseDto(
    val message: CrossrefMessageDto
)

@Serializable
data class CrossrefMessageDto(
    val items: List<ArticleDto> = emptyList()
)

@Serializable
data class ArticleDto(
    val title: List<String> = emptyList(),
    val author: List<AuthorDto> = emptyList(),
    val published: PublishedDto? = null,
    @SerialName("container-title")
    val containerTitle: List<String> = emptyList(),
    @SerialName("DOI")
    val doi: String = "",
    val type: String = "",
    @SerialName("URL")
    val url: String = ""
)

@Serializable
data class AuthorDto(
    val given: String? = null,
    val family: String? = null,
    val name: String? = null
)

@Serializable
data class PublishedDto(
    @SerialName("date-parts")
    val dateParts: List<List<Int?>> = emptyList()
)