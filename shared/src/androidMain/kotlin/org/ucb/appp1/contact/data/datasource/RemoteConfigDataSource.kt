package org.ucb.appp1.contact.data.datasource

import android.util.Log
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
        Log.d("RemoteConfig", "✅ RemoteConfig inicializado con valores por defecto")
        config
    }

    actual fun getPhone(): Flow<String> = flow {
        try {
            remoteConfig.fetchAndActivate().await()
            val phone = remoteConfig.getString("phone_support")
            Log.d("RemoteConfig", "📞 Teléfono leído de Firebase: $phone")
            emit(phone.ifEmpty { "+591 7070812" })
        } catch (e: Exception) {
            Log.e("RemoteConfig", "❌ Error leyendo teléfono: ${e.message}")
            emit("+591 7070812")
        }
    }

    actual fun getEmail(): Flow<String> = flow {
        try {
            remoteConfig.fetchAndActivate().await()
            val email = remoteConfig.getString("email_support")
            Log.d("RemoteConfig", "📧 Email leído de Firebase: $email")
            emit(email.ifEmpty { "soporte@empresa.com" })
        } catch (e: Exception) {
            Log.e("RemoteConfig", "❌ Error leyendo email: ${e.message}")
            emit("soporte@empresa.com")
        }
    }

    actual fun isWhatsappEnabled(): Flow<Boolean> = flow {
        try {
            remoteConfig.fetchAndActivate().await()
            val enabled = remoteConfig.getBoolean("enable_whatsapp")
            Log.d("RemoteConfig", "💬 WhatsApp habilitado: $enabled")
            emit(enabled)
        } catch (e: Exception) {
            Log.e("RemoteConfig", "❌ Error leyendo WhatsApp: ${e.message}")
            emit(true)
        }
    }
}
