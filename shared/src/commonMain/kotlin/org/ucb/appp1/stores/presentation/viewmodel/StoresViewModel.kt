package org.ucb.appp1.stores.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ucb.appp1.stores.domain.usecase.GetStoresUseCase

class StoresViewModel(
    val usecase: GetStoresUseCase
): ViewModel() {

    private val _state = MutableStateFlow(StoresState())
    val state = _state.asStateFlow()

    init {
        loadStores()
    }

    private fun loadStores() {
        viewModelScope.launch {
            _state.value = StoresState(isLoading = true)
            _state.value = runCatching { usecase.invoke() }.fold(
                onSuccess = { StoresState(isLoading = false, stores = it) },
                onFailure = {
                    StoresState(isLoading = false, error = it.message ?: "Error al cargar las tiendas")
                }
            )
        }
    }
}
