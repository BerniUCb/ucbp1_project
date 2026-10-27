package org.ucb.appp1.sync.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.serialization.json.Json
import org.ucb.appp1.sync.data.dto.AppConfigDto
import kotlin.time.Duration.Companion.seconds

private const val KEY_APP_CONFIG = "app_config"

class ConfigRemoteDataSource {

    private val remoteConfig by lazy { Firebase.remoteConfig }
    private val json = Json { ignoreUnknownKeys = true }

    // Descarga el JSON de Remote Config; lanza si no hay red o si el parámetro no existe
    suspend fun fetchConfig(): AppConfigDto {
        // Para testing el intervalo es 0; en produccion usar 1.hours o 12.hours
        remoteConfig.settings { minimumFetchInterval = 0.seconds }
        remoteConfig.fetchAndActivate()
        val raw = remoteConfig.getValue(KEY_APP_CONFIG).asString()
        // Sin el parámetro publicado no se borra lo que ya hay en Room
        require(raw.isNotBlank()) { "Remote Config no tiene el parámetro $KEY_APP_CONFIG" }
        return json.decodeFromString<AppConfigDto>(raw)
    }
}
