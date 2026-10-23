package org.ucb.appp1.stores.domain.usecase

import org.ucb.appp1.stores.domain.model.StoreModel
import org.ucb.appp1.stores.domain.repository.StoresRepository

class GetStoresUseCase(
    val repository: StoresRepository
) {
    suspend fun invoke(): List<StoreModel> {
        return repository.getStores()
    }
}
