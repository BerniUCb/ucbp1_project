package org.ucb.appp1.contact.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.contact.presentation.viewmodel.ContactViewModel

@Composable
fun ContactScreen(viewModel: ContactViewModel = koinViewModel()) {
    val contact = viewModel.contactState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📋 Contacto desde Remote Config")
        Text("Teléfono: ${contact.value.phone}")
        Text("Email: ${contact.value.email}")
        Text("WhatsApp: ${contact.value.whatsappEnabled}")
    }
}