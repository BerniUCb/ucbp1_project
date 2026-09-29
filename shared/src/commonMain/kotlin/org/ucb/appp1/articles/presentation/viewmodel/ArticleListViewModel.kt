package org.ucb.appp1.articles.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.articles.domain.usecase.SearchArticles

class ArticleListViewModel(
    private val searchArticles: SearchArticles
) : ViewModel() {
    private val _state = MutableStateFlow(ArticleListState())
    val state = _state.asStateFlow()

    init {
        search()
    }

    fun emitEvent(event: ArticleListEvent) {
        when (event) {
            is ArticleListEvent.OnQueryChange -> {
                _state.update { it.copy(query = event.value) }
            }
            ArticleListEvent.OnSearch -> search()
        }
    }

    private fun search() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }
            searchArticles.invoke(_state.value.query).fold(
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