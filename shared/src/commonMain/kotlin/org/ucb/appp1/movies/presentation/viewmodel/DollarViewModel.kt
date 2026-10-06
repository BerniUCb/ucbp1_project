package org.ucb.appp1.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.movies.domain.model.DollarModel
import org.ucb.appp1.movies.domain.repository.DollarRepository

class DollarViewModel(
    val repository: DollarRepository
): ViewModel() {

    private val _state = MutableStateFlow(DollarState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DollarEffect>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: DollarEvent) = viewModelScope.launch {
        when(event) {
            DollarEvent.OnAddRecord -> {
                repository.insert(DollarModel("a", "b"))
                val list = repository.getList()
                _state.update {
                    it.copy(list = list)
                }
            }
        }
    }
}
