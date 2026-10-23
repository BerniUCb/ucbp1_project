package org.ucb.appp1.stores.domain.repository

import org.ucb.appp1.stores.domain.model.StoreModel

interface StoresRepository {
    suspend fun getStores(): List<StoreModel>
}
