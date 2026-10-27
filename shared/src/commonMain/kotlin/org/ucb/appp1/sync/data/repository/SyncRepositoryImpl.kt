package org.ucb.appp1.sync.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.ucb.appp1.sync.data.dao.SyncDao
import org.ucb.appp1.sync.data.datasource.ConfigRemoteDataSource
import org.ucb.appp1.sync.data.entity.BannerEntity
import org.ucb.appp1.sync.data.entity.FeatureEntity
import org.ucb.appp1.sync.domain.model.BannerModel
import org.ucb.appp1.sync.domain.model.ConfigModel
import org.ucb.appp1.sync.domain.model.FeatureModel
import org.ucb.appp1.sync.domain.repository.SyncRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class SyncRepositoryImpl(
    private val remote: ConfigRemoteDataSource,
    private val dao: SyncDao
) : SyncRepository {

    // La UI solo lee de Room: este flow se vuelve a emitir cada vez que el Worker guarda algo
    override fun observeConfig(): Flow<ConfigModel> =
        combine(dao.observeFeatures(), dao.observeBanners()) { features, banners ->
            val lastSync = (features.map { it.syncedAt } + banners.map { it.syncedAt }).maxOrNull()
            ConfigModel(
                features = features.map { FeatureModel(it.key, it.name, it.enabled) },
                banners = banners.map { BannerModel(it.id, it.title, it.message, it.active) },
                lastSyncAt = lastSync
            )
        }

    override suspend fun syncFromRemote() {
        val config = remote.fetchConfig()
        val now = Clock.System.now().toEpochMilliseconds()
        val features = config.features.map { FeatureEntity(it.key, it.name, it.enabled, now) }
        val banners = config.banners.map { BannerEntity(it.id, it.title, it.message, it.active, now) }
        dao.upsertFeatures(features)
        dao.deleteFeaturesNotIn(features.map { it.key })
        dao.upsertBanners(banners)
        dao.deleteBannersNotIn(banners.map { it.id })
    }
}
