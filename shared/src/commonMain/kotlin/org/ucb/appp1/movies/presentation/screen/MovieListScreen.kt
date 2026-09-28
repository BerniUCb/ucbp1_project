package org.ucb.appp1.movies.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.movies.presentation.viewmodel.MovieListViewModel

@Composable
fun MovieListScreen(viewModel: MovieListViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.value.loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            state.value.error != null -> Text(
                text = state.value.error ?: "",
                modifier = Modifier.align(Alignment.Center)
            )
            else -> LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize().padding(4.dp)
            ) {
                items(state.value.movies) { movie ->
                    Column(modifier = Modifier.padding(4.dp)) {
                        AsyncImage(
                            model = movie.posterPath.value,
                            contentDescription = movie.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().aspectRatio(2f / 3f)
                        )
                        Text(text = movie.title)
                    }
                }
            }
        }
    }
}