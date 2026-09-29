package org.ucb.appp1.articles.presentation.viewmodel

sealed interface ArticleListEvent {
    data class OnQueryChange(val value: String) : ArticleListEvent
    data object OnSearch : ArticleListEvent
}