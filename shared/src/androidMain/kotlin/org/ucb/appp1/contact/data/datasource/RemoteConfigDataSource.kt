package org.ucb.appp1.contact.data.datasource

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

actual class RemoteConfigDataSource {

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        val config = FirebaseRemoteConfig.getInstance()
        config.setDefaultsAsync(
            mapOf(
                "phone_support" to "+591 7070812",
                "email_support" to "soporte@empresa.com",
                "enable_whatsapp" to true
            )
        )
        config
    }

    actual fun getPhone(): Flow<String> = flow {
        try {
            remoteConfig.fetchAndActivate().await()
            val phone = remoteConfig.getString("phone_support")
            emit(phone.ifEmpty { "+591 7070812" })
        } catch (e: Exception) {
            emit("+591 7070812")
        }
    }

    actual fun getEmail(): Flow<String> = flow {
        try {
            remoteConfig.fetchAndActivate().await()
            val email = remoteConfig.getString("email_support")
            emit(email.ifEmpty { "soporte@empresa.com" })
        } catch (e: Exception) {
            emit("soporte@empresa.com")
        }
    }

    actual fun isWhatsappEnabled(): Flow<Boolean> = flow {
        try {
            remoteConfig.fetchAndActivate().await()
            val enabled = remoteConfig.getBoolean("enable_whatsapp")
            emit(enabled)
        } catch (e: Exception) {
            emit(true)
        }
    }
}
