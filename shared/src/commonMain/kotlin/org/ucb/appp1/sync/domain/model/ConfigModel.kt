package org.ucb.appp1.sync.domain.model

data class ConfigModel(
    val features: List<FeatureModel> = emptyList(),
    val banners: List<BannerModel> = emptyList(),
    val lastSyncAt: Long? = null
)

data class FeatureModel(
    val key: String,
    val name: String,
    val enabled: Boolean
)

data class BannerModel(
    val id: String,
    val title: String,
    val message: String,
    val active: Boolean
)
