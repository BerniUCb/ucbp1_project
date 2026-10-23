package org.ucb.appp1.stores.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.ucb.appp1.stores.data.dto.StoreDto
import org.ucb.appp1.stores.domain.model.StoreModel

class FirestoreDataSource {

    private val firestore by lazy { Firebase.firestore }
    private val storesRef by lazy { firestore.collection("stores") }

    suspend fun getStores(): List<StoreModel> {
        return storesRef.get().documents.map { document ->
            document.data<StoreDto>().toModel(document.id)
        }
    }
}
