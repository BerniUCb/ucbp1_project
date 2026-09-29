package org.ucb.appp1.articles.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.articles.presentation.viewmodel.ArticleListEvent
import org.ucb.appp1.articles.presentation.viewmodel.ArticleListViewModel

@Composable
fun ArticleListScreen(viewModel: ArticleListViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = state.value.query,
                onValueChange = { viewModel.emitEvent(ArticleListEvent.OnQueryChange(it)) },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { viewModel.emitEvent(ArticleListEvent.OnSearch) }) {
                Text("Buscar")
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.value.loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                state.value.error != null -> Text(
                    text = state.value.error ?: "",
                    modifier = Modifier.align(Alignment.Center)
                )
                else -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    items(state.value.articles) { article ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = article.title, fontWeight = FontWeight.Bold)
                                Text("Autor(es): " + article.authors.joinToString(", ").ifBlank { "No disponible" })
                                Text("Publicado: " + article.published.ifBlank { "No disponible" })
                                Text("Revista: " + article.journal.ifBlank { "No disponible" })
                                Text("DOI: " + article.doi)
                                Text("Tipo: " + article.type)
                                Text(
                                    text = article.url,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.clickable { uriHandler.openUri(article.url) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}