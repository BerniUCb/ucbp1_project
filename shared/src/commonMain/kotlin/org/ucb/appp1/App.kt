package org.ucb.appp1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.ucb.appp1.exchange.presentation.screen.ExchangeScreen
import org.ucb.appp1.movies.presentation.screen.DollarScreen
import org.ucb.appp1.signin.presentation.screen.LoginScreen
import org.ucb.appp1.stores.presentation.screen.StoresScreen
import org.ucb.appp1.userinformation.presentation.screen.UserInformationScreen
import org.ucb.appp1.contact.presentation.screen.ContactScreen
import org.ucb.appp1.logs.presentation.screen.LogsScreen
import org.ucb.appp1.notifications.presentation.screen.NotificationsScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            LogsScreen() // TEMPORAL: botón de prueba gRPC
            NotificationsScreen() // TEMPORAL: muestra el token FCM
            Box(modifier = Modifier.weight(0.5f)) { ExchangeScreen() } // Realtime DB (app_dollar)
            Box(modifier = Modifier.weight(1f)) { ContactScreen() }
            Box(modifier = Modifier.weight(1f)) { StoresScreen() }
        }
    }
}