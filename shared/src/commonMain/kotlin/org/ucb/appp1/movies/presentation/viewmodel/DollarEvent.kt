package org.ucb.appp1.movies.presentation.viewmodel

sealed interface DollarEvent {
    object OnAddRecord: DollarEvent
}
