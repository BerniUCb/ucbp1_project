package org.ucb.appp1.contact.data.datasource

import kotlinx.coroutines.flow.Flow

expect class RemoteConfigDataSource() {
    fun getPhone(): Flow<String>
    fun getEmail(): Flow<String>
    fun isWhatsappEnabled(): Flow<Boolean>
}