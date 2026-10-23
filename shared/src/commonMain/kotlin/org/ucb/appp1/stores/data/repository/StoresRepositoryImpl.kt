package org.ucb.appp1.stores.data.repository

import org.ucb.appp1.stores.data.datasource.FirestoreDataSource
import org.ucb.appp1.stores.domain.model.StoreModel
import org.ucb.appp1.stores.domain.repository.StoresRepository

class StoresRepositoryImpl(
    val firestoreDataSource: FirestoreDataSource
): StoresRepository {
    override suspend fun getStores(): List<StoreModel> {
        return firestoreDataSource.getStores()
    }
}
