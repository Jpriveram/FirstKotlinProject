package edu.ucb.project.login.presentation.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import edu.ucb.project.login.presentation.viewmodel.LoginEffect
import edu.ucb.project.login.presentation.viewmodel.LoginEvent
import edu.ucb.project.login.presentation.viewmodel.LoginViewModel
import edu.ucb.project.navigation.NavRoute

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel=koinViewModel()) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when(effect) {
                LoginEffect.NavigateToHome -> {navController.navigate(NavRoute.UserSearch)}
                is LoginEffect.ShowToast -> {
                    println("ERROR ${effect.message}")
                }

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = state.value.username,
            onValueChange = {
                viewModel.emitEvent(LoginEvent.OnUsernameChanged(it))
            },
            label = { Text("Email / Usuario") }
        )

        TextField(
            value = state.value.password,
            onValueChange = {
                viewModel.emitEvent(LoginEvent.OnPasswordChanged(it))
            },
            label = { Text("Contraseña") }
        )

        Button(onClick = {
            viewModel.emitEvent(LoginEvent.OnLoginClick)
        }) {
            Text("Login")
        }
    }
}
