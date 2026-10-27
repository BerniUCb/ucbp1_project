package org.ucb.appp1.sync.data.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "banners")
data class BannerEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "active")
    val active: Boolean,

    @ColumnInfo(name = "synced_at")
    val syncedAt: Long
)
