package org.ucb.appp1.sync.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.sync.data.entity.BannerEntity
import org.ucb.appp1.sync.data.entity.FeatureEntity

@Dao
interface SyncDao {
    @Query("SELECT * FROM features ORDER BY name")
    fun observeFeatures(): Flow<List<FeatureEntity>>

    @Query("SELECT * FROM banners ORDER BY title")
    fun observeBanners(): Flow<List<BannerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFeatures(features: List<FeatureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBanners(banners: List<BannerEntity>)

    // Se borra lo que ya no viene en el JSON, sin vaciar la tabla antes (así la UI no parpadea)
    @Query("DELETE FROM features WHERE `key` NOT IN (:keys)")
    suspend fun deleteFeaturesNotIn(keys: List<String>)

    @Query("DELETE FROM banners WHERE id NOT IN (:ids)")
    suspend fun deleteBannersNotIn(ids: List<String>)
}
