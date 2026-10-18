package org.ucb.appp1.contact.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class RemoteConfigDataSource {

    actual fun getPhone(): Flow<String> = flowOf("+591 7070812")

    actual fun getEmail(): Flow<String> = flowOf("soporte@empresa.com")

    actual fun isWhatsappEnabled(): Flow<Boolean> = flowOf(true)
}
