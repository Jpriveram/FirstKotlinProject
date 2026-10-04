package edu.ucb.project.exchangerate.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.ucb.project.exchangerate.presentation.viewmodel.ExchangeRateEffect
import edu.ucb.project.exchangerate.presentation.viewmodel.ExchangeRateEvent
import edu.ucb.project.exchangerate.presentation.viewmodel.ExchangeRateViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExchangeRateScreen(
    navController: NavController,
    viewModel: ExchangeRateViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ExchangeRateEffect.NavigateBack -> {
                    navController.popBackStack()
                }
                else -> {
                    // Ignore others here
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar tipo de cambio") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.value.officialRateInput,
                onValueChange = { viewModel.emitEvent(ExchangeRateEvent.OnOfficialRateChanged(it)) },
                label = { Text("Tipo de cambio oficial") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.value.parallelRateInput,
                onValueChange = { viewModel.emitEvent(ExchangeRateEvent.OnParallelRateChanged(it)) },
                label = { Text("Tipo de cambio paralelo") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.emitEvent(ExchangeRateEvent.OnSaveExchangeRate) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.emitEvent(ExchangeRateEvent.OnBack) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}