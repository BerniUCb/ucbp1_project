package org.ucb.appp1.movies.domain.repository

import org.ucb.appp1.movies.domain.model.DollarModel

interface DollarRepository {
    suspend fun getList(): List<DollarModel>
    suspend fun insert(dollar: DollarModel)
}
