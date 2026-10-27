package org.ucb.appp1.di

import com.develoop.logs.GrpcLogServiceClient
import com.develoop.logs.LogServiceClient
import com.squareup.wire.GrpcClient
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.ucb.appp1.config.AppDatabase
import org.ucb.appp1.config.getDatabaseBuilder
import org.ucb.appp1.logs.data.config.LogsConfig
import org.ucb.appp1.sync.WorkManagerSyncScheduler
import org.ucb.appp1.sync.domain.scheduler.SyncScheduler

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        getDatabaseBuilder(get()).build()
    }
    // WorkManager es solo de Android
    single<SyncScheduler> { WorkManagerSyncScheduler(androidContext()) }
    // El GrpcClient de Wire solo trae implementación para JVM (OkHttp); no existe en iOS
    single<LogServiceClient> {
        val grpcClient = GrpcClient.Builder()
            .client(OkHttpClient())
            .baseUrl(LogsConfig.BASE_URL)
            .build()
        GrpcLogServiceClient(grpcClient)
    }
}
