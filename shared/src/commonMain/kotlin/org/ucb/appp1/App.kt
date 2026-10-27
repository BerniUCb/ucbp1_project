package org.ucb.appp1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.ucb.appp1.movies.presentation.screen.DollarScreen
import org.ucb.appp1.signin.presentation.screen.LoginScreen
import org.ucb.appp1.userinformation.presentation.screen.UserInformationScreen
import org.ucb.appp1.sync.presentation.screen.SyncScreen
import org.ucb.appp1.tasks.presentation.screen.TasksScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var selectedTab by remember { mutableIntStateOf(0) }
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Examen: E50-1 (Remote Config -> Room) y E50-2 (Firestore + Realtime DB)
            TabRow(selectedTabIndex = selectedTab) {
                Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("E50-1 Sync") })
                Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("E50-2 Tareas") })
            }
            Box(modifier = Modifier.weight(1f)) {
                if (selectedTab == 0) SyncScreen() else TasksScreen()
            }
        }
    }
}