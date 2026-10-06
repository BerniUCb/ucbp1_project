package org.ucb.appp1.movies.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.movies.presentation.viewmodel.DollarEvent
import org.ucb.appp1.movies.presentation.viewmodel.DollarViewModel

@Composable
fun DollarScreen(
    viewModel: DollarViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    Column {
        Button(onClick = {
            viewModel.emitEvent(DollarEvent.OnAddRecord)
        }) {
            Text("Add")
        }
        Text(state.value.list.size.toString())
    }
}
