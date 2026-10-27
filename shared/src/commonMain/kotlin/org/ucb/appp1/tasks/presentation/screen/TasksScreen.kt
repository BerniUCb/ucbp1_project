package org.ucb.appp1.tasks.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.tasks.presentation.viewmodel.TasksViewModel

@Composable
fun TasksScreen(viewModel: TasksViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value
    var newTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Completadas (RTDB): ", style = MaterialTheme.typography.titleMedium)
            // Contador animado: el número viejo sale y el nuevo entra desde abajo
            AnimatedContent(
                targetState = state.completedCount,
                transitionSpec = {
                    val up = targetState > initialState
                    (slideInVertically { if (up) it else -it } + fadeIn()) togetherWith
                        (slideOutVertically { if (up) -it else it } + fadeOut()) using
                        SizeTransform(clip = false)
                },
                label = "completedCounter"
            ) { count ->
                Text(count.toString(), style = MaterialTheme.typography.headlineMedium)
            }
        }

        state.error?.let { message ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(message, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.error)
                    TextButton(onClick = viewModel::dismissError) { Text("OK") }
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("Nueva tarea") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                viewModel.add(newTitle)
                newTitle = ""
            }) { Text("Agregar") }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.tasks.isEmpty()) {
            Text("No hay tareas en Firestore (colección tareas).")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.tasks, key = { it.id }) { task ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = task.completed,
                            onCheckedChange = { viewModel.toggle(task.id, it) },
                            enabled = task.id !in state.pendingIds
                        )
                        Text(
                            task.title,
                            textDecoration = if (task.completed) TextDecoration.LineThrough else null
                        )
                    }
                }
            }
        }
    }
}
