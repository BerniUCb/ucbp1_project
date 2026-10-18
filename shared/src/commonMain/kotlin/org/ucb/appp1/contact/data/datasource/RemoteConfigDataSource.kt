package org.ucb.appp1.contact.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.get
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteConfigDataSource {

    private val remoteConfig by lazy { Firebase.remoteConfig }

    suspend fun fetchAndActivateConfig(): Boolean {
        return try {
            remoteConfig.fetchAndActivate()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun observePhone(): Flow<String> = flow {
        try {
            fetchAndActivateConfig()
            val phone = remoteConfig["phone_support"].asString()
            emit(phone.ifEmpty { "+591 7070812" })
        } catch (e: Exception) {
            emit("+591 7070812")
        }
    }

    fun observeEmail(): Flow<String> = flow {
        try {
            fetchAndActivateConfig()
            val email = remoteConfig["email_support"].asString()
            emit(email.ifEmpty { "soporte@empresa.com" })
        } catch (e: Exception) {
            emit("soporte@empresa.com")
        }
    }

    fun observeWhatsappEnabled(): Flow<Boolean> = flow {
        try {
            fetchAndActivateConfig()
            val enabled = remoteConfig["enable_whatsapp"].asBoolean()
            emit(enabled)
        } catch (e: Exception) {
            emit(true)
        }
    }
}