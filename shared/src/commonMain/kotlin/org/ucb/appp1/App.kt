package org.ucb.appp1

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.ucb.appp1.articles.presentation.screen.ArticleListScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        ArticleListScreen()
    }
}