package org.ucb.appp1.notifications.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.notifications.presentation.viewmodel.NotificationsViewModel

@Composable
fun NotificationsScreen(viewModel: NotificationsViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Token FCM (mantén presionado para copiar):")
        SelectionContainer {
            Text(
                when {
                    state.isLoading -> "Obteniendo token..."
                    state.error != null -> "Error: ${state.error}"
                    else -> state.token.orEmpty()
                }
            )
        }
    }
}
