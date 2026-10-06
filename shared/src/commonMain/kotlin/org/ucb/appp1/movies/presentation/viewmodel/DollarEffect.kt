package org.ucb.appp1.movies.presentation.viewmodel

interface DollarEffect {
    data class ShowToast(val message: String): DollarEffect
}
