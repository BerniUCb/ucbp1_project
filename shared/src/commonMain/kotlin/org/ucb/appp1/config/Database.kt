package org.ucb.appp1.config

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import org.ucb.appp1.movies.data.dao.DollarDao
import org.ucb.appp1.movies.data.entity.DollarEntity
import org.ucb.appp1.sync.data.dao.SyncDao
import org.ucb.appp1.sync.data.entity.BannerEntity
import org.ucb.appp1.sync.data.entity.FeatureEntity

@Database(entities = [DollarEntity::class, FeatureEntity::class, BannerEntity::class], version = 2)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): DollarDao
    abstract fun getSyncDao(): SyncDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
