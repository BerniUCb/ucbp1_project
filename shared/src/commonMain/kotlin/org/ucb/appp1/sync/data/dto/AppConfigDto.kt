package org.ucb.appp1.sync.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppConfigDto(
    val features: List<FeatureDto> = emptyList(),
    val banners: List<BannerDto> = emptyList()
)

@Serializable
data class FeatureDto(
    val key: String = "",
    val name: String = "",
    val enabled: Boolean = false
)

@Serializable
data class BannerDto(
    val id: String = "",
    val title: String = "",
    val message: String = "",
    val active: Boolean = true
)
