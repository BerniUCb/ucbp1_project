package org.ucb.appp1.contact.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.contact.presentation.viewmodel.ContactViewModel

@Composable
fun ContactScreen(viewModel: ContactViewModel = koinViewModel()) {
    val contact = viewModel.contactState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Contacta con Soporte")

        Text(
            text = "Teléfono: ${contact.value.phone}",
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                // Aquí irían las acciones de llamada
                println("Llamar a: ${contact.value.phone}")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("📞 Llamar")
        }

        if (contact.value.whatsappEnabled) {
            Button(
                onClick = {
                    // Aquí irían las acciones de WhatsApp
                    println("WhatsApp a: ${contact.value.phone}")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("💬 WhatsApp")
            }
        }

        Text(
            text = "Email: ${contact.value.email}",
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                println("Email a: ${contact.value.email}")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("✉️ Enviar Email")
        }
    }
}