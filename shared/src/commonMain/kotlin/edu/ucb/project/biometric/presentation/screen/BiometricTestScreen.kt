package edu.ucb.project.biometric.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucb.project.biometric.presentation.viewmodel.BiometricViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BiometricTestScreen(
    viewModel: BiometricViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Biometric Test",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = state.statusMessage)

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isAuthenticated) {
            Text(
                text = "Acceso autorizado",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            enabled = state.isAvailable &&
                !state.isChecking &&
                !state.isAuthenticating,
            onClick = {
                viewModel.authenticate()
            }
        ) {
            Text(
                if (state.isAuthenticating) {
                    "Autenticando..."
                } else {
                    "Autenticar"
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            enabled = !state.isChecking &&
                !state.isAuthenticating,
            onClick = {
                viewModel.checkBiometricAvailability()
            }
        ) {
            Text("Comprobar biometría")
        }
    }
}
