package org.ucb.appp1.logs.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.logs.presentation.viewmodel.LogsViewModel

@Composable
fun LogsScreen(viewModel: LogsViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Button(onClick = { viewModel.sendTestLog() }, enabled = !state.isSending) {
            Text(if (state.isSending) "Enviando..." else "Enviar log gRPC")
        }
        state.result?.let { Text("Resultado: $it") }
        state.error?.let { Text("Error: $it") }
    }
}
