package org.ucb.appp1

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.ucb.appp1.exchange.presentation.screen.ExchangeScreen
import org.ucb.appp1.movies.presentation.screen.DollarScreen
import org.ucb.appp1.signin.presentation.screen.LoginScreen
import org.ucb.appp1.userinformation.presentation.screen.UserInformationScreen
import org.ucb.appp1.contact.presentation.screen.ContactScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        ContactScreen()
    }
}