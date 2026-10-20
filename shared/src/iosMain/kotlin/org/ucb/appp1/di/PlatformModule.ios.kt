package org.ucb.appp1.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import org.ucb.appp1.config.AppDatabase
import org.ucb.appp1.config.getDatabaseBuilder

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        // En Native hay que indicar el driver; en Android lo resuelve el sqlite-wrapper
        getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}
