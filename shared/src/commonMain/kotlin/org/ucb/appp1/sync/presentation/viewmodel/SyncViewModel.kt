package org.ucb.appp1.sync.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.ucb.appp1.sync.domain.scheduler.SyncScheduler
import org.ucb.appp1.sync.domain.usecase.ObserveConfigUseCase

class SyncViewModel(
    observeConfig: ObserveConfigUseCase,
    private val scheduler: SyncScheduler
) : ViewModel() {

    val state: StateFlow<SyncState> = combine(observeConfig(), scheduler.status) { config, status ->
        SyncState(config = config, status = status)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS), SyncState())

    fun refresh() = scheduler.syncNow()

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
