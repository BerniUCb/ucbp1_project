package org.ucb.appp1.articles.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.articles.domain.usecase.SearchArticles

private const val DEFAULT_QUERY = "machine learning"

class ArticleListViewModel(
    private val searchArticles: SearchArticles
) : ViewModel() {
    private val _state = MutableStateFlow(ArticleListState())
    val state = _state.asStateFlow()

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }
            searchArticles.invoke(DEFAULT_QUERY).fold(
                onSuccess = { list ->
                    _state.update { it.copy(articles = list, loading = false) }
                },
                onFailure = { e ->
                    _state.update { it.copy(loading = false, error = e.message ?: "Error") }
                }
            )
        }
    }
}