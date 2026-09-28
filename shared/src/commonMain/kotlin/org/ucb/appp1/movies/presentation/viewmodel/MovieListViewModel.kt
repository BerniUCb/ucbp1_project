package org.ucb.appp1.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.movies.domain.usecase.GetPopularMovies

class MovieListViewModel(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {
    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }
            getPopularMovies.invoke().fold(
                onSuccess = { movies ->
                    _state.update { it.copy(movies = movies, loading = false) }
                },
                onFailure = { e ->
                    _state.update { it.copy(loading = false, error = e.message ?: "Error") }
                }
            )
        }
    }
}