package org.ucb.appp1.designsystem.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.buttons.PrimaryButton
import edu.ucb.ds.components.card.DsCard
import edu.ucb.ds.components.label.DsLabel
import edu.ucb.ds.components.list.DsList
import edu.ucb.ds.components.surfaces.DsSurface
import edu.ucb.ds.components.textfields.DsTextField
import edu.ucb.ds.icons.UserIcon
import edu.ucb.ds.theme.UcbTheme

// TEMPORAL: pantalla para probar el Design System publicado en mavenLocal
@Composable
fun DesignSystemScreen() {
    UcbTheme {
        DsSurface(modifier = Modifier.fillMaxSize()) {
            var name by remember { mutableStateOf("") }
            var clicks by remember { mutableStateOf(0) }

            Column(
                modifier = Modifier.fillMaxSize().padding(UcbTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(UcbTheme.spacing.large)
            ) {
                BasicText("Design System", style = UcbTheme.typography.heading.copy(color = UcbTheme.colors.primary))
                DsLabel("Label: tokens de color, tipografía y espaciado")

                DsTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nombre (error si está vacío)",
                    isError = name.isEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(UcbTheme.spacing.medium)) {
                    PrimaryButton(text = "Botón primario", onClick = { clicks++ })
                    PrimaryButton(text = "Deshabilitado", onClick = {}, enabled = false)
                }
                DsLabel("Clics en el botón: $clicks")

                DsCard(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(UcbTheme.spacing.medium)) {
                        Image(
                            imageVector = UserIcon,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(UcbTheme.colors.secondary),
                            modifier = Modifier.size(32.dp)
                        )
                        BasicText(
                            text = if (name.isEmpty()) "Usuario sin nombre" else name,
                            style = UcbTheme.typography.body.copy(color = UcbTheme.colors.onBackground)
                        )
                    }
                }

                DsList(items = listOf("Primary", "Secondary", "Background", "Error")) { token ->
                    DsLabel("Token de color: $token")
                }
            }
        }
    }
}
