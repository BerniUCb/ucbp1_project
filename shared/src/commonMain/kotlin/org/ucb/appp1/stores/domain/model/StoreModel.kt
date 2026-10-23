package org.ucb.appp1.stores.domain.model

data class StoreModel(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val hours: List<String>
)
