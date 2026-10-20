package org.ucb.appp1.contact.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration.Companion.seconds

private const val KEY_PHONE = "phone_support"
private const val KEY_EMAIL = "email_support"
private const val KEY_WHATSAPP = "enable_whatsapp"

private const val DEFAULT_PHONE = "+591 7070812"
private const val DEFAULT_EMAIL = "soporte@empresa.com"
private const val DEFAULT_WHATSAPP = true

class RemoteConfigDataSource {

    private val remoteConfig by lazy { Firebase.remoteConfig }
    private val mutex = Mutex()
    private var synced = false

    fun getPhone(): Flow<String> = flow {
        emit(readString(KEY_PHONE, DEFAULT_PHONE))
    }

    fun getEmail(): Flow<String> = flow {
        emit(readString(KEY_EMAIL, DEFAULT_EMAIL))
    }

    fun isWhatsappEnabled(): Flow<Boolean> = flow {
        emit(readBoolean(KEY_WHATSAPP, DEFAULT_WHATSAPP))
    }

    // Descarga y activa los valores una sola vez, aunque los tres flows se colecten a la vez
    private suspend fun sync() = mutex.withLock {
        if (synced) return@withLock
        // Para testing el intervalo es 0; en produccion usar 1.hours o 12.hours
        remoteConfig.settings { minimumFetchInterval = 0.seconds }
        remoteConfig.setDefaults(
            KEY_PHONE to DEFAULT_PHONE,
            KEY_EMAIL to DEFAULT_EMAIL,
            KEY_WHATSAPP to DEFAULT_WHATSAPP
        )
        remoteConfig.fetchAndActivate()
        synced = true
    }

    private suspend fun readString(key: String, default: String): String = try {
        sync()
        remoteConfig.getValue(key).asString().ifEmpty { default }
    } catch (e: Exception) {
        println("RemoteConfig: error leyendo $key -> ${e.message}")
        default
    }

    private suspend fun readBoolean(key: String, default: Boolean): Boolean = try {
        sync()
        remoteConfig.getValue(key).asBoolean()
    } catch (e: Exception) {
        println("RemoteConfig: error leyendo $key -> ${e.message}")
        default
    }
}
