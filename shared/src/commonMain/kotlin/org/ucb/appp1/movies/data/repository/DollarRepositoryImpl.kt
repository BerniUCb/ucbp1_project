package org.ucb.appp1.movies.data.repository

import org.ucb.appp1.movies.data.datasource.DollarLocalDataSource
import org.ucb.appp1.movies.domain.model.DollarModel
import org.ucb.appp1.movies.domain.repository.DollarRepository

class DollarRepositoryImpl(
    val localDataSource: DollarLocalDataSource
): DollarRepository {
    override suspend fun getList(): List<DollarModel> {
        return localDataSource.getList()
    }

    override suspend fun insert(dollar: DollarModel) {
        localDataSource.insert(dollar)
    }
}
