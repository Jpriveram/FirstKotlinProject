package edu.ucb.project.weather.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import edu.ucb.project.user_search.presentation.viewmodel.UserSearchEffects
import org.koin.compose.viewmodel.koinViewModel
import edu.ucb.project.weather.presentation.viewmodel.WeatherEffects
import edu.ucb.project.weather.presentation.viewmodel.WeatherEvents
import edu.ucb.project.weather.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is WeatherEffects.ShowToast -> {
                    //Todo
                }
                WeatherEffects.NavigateToBack -> {
                    //Todo
                }
            }
        }
    }

    Column {
        TextField(
            value = state.value.latitude,
            onValueChange = {
                viewModel.emitEvent(WeatherEvents.OnLatitudeChange(it))
            },
            label = { Text("Latitud") }
        )

        TextField(
            value = state.value.longitude,
            onValueChange = {
                viewModel.emitEvent(WeatherEvents.OnLongitudeChange(it))
            },
            label = { Text("Longitud") }
        )

        Button(onClick = {
            viewModel.emitEvent(WeatherEvents.OnSubmit)
        }) {
            Text("Buscar")
        }

        if (state.value.temperature.isNotEmpty()) {
            Text("Temperatura: ${state.value.temperature}")
            Text("Velocidad viento: ${state.value.windspeed}")
            Text("Dirección viento: ${state.value.winddirection}")
            Text("Código clima: ${state.value.weathercode}")
            Text("Hora: ${state.value.time}")
            Text("Latitud: ${state.value.latitude}")
            Text("Longitud: ${state.value.longitude}")
        }
    }
}