package org.ucb.appp1.sync.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.sync.presentation.viewmodel.SyncViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncScreen(viewModel: SyncViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value
    val config = state.config

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = viewModel::refresh,
        modifier = Modifier.fillMaxSize()
    ) {
        // LazyColumn siempre scrolleable: así el pull-to-refresh funciona aunque la lista esté vacía
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text("Sincronización (Remote Config → Room)", style = MaterialTheme.typography.titleMedium)
                if (state.isCached) {
                    Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.small) {
                        Text("Datos cacheados", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }
                if (config.features.isEmpty() && config.banners.isEmpty()) {
                    Text("Sin datos todavía. Desliza hacia abajo para sincronizar.")
                }
            }
            if (config.banners.isNotEmpty()) {
                item { Text("Banners", style = MaterialTheme.typography.titleSmall) }
                items(config.banners, key = { "banner-${it.id}" }) { banner ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(banner.title, style = MaterialTheme.typography.titleSmall)
                            Text(banner.message)
                            Text(if (banner.active) "Activo" else "Inactivo")
                        }
                    }
                }
            }
            if (config.features.isNotEmpty()) {
                item { Text("Features", style = MaterialTheme.typography.titleSmall) }
                items(config.features, key = { "feature-${it.key}" }) { feature ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(feature.name)
                            Text(if (feature.enabled) "Habilitada" else "Deshabilitada")
                        }
                    }
                }
            }
        }
    }
}
