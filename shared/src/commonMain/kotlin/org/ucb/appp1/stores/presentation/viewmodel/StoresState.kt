package org.ucb.appp1.stores.presentation.viewmodel

import org.ucb.appp1.stores.domain.model.StoreModel

data class StoresState(
    val isLoading: Boolean = true,
    val stores: List<StoreModel> = emptyList(),
    val error: String? = null
)
