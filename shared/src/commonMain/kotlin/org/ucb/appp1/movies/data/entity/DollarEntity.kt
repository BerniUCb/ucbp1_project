package org.ucb.appp1.movies.data.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "dollars")
data class DollarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "dollar_official")
    var dollarOfficial: String? = null,

    @ColumnInfo(name = "dollar_parallel")
    var dollarParallel: String? = null,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0
)
