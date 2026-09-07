package org.ucb.appp1.signin.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.signin.presentation.viewmodel.LoginEffects
import org.ucb.appp1.signin.presentation.viewmodel.LoginEvents
import org.ucb.appp1.signin.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen( viewModel: LoginViewModel = koinViewModel()) {

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when(effect) {
                LoginEffects.NavigateToHome -> TODO()
                is LoginEffects.ShowToast -> {
                    println("ERROR ${effect.message}")
                }
                LoginEffects.SignUp -> TODO()
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = state.value.email, onValueChange = {
            viewModel.emitEvent(LoginEvents.OnEmailChanged(it))
        })
        Button(onClick = {
            viewModel.emitEvent(LoginEvents.OnSubmit)
        }) {
            Text("Login")
        }
    }
}
