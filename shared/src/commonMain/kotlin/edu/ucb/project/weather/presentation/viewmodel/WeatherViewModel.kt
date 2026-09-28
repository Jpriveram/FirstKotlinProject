package edu.ucb.project.weather.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.weather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(val getWeatherUseCase: GetWeatherUseCase): ViewModel() {
    private val _state = MutableStateFlow<WeatherState>(WeatherState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WeatherEffects>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: WeatherEvents) {
        when (event) {
            is WeatherEvents.OnLatitudeChange -> {
                _state.update { it.copy(latitude = event.value) }
            }

            is WeatherEvents.OnLongitudeChange -> {
                _state.update { it.copy(longitude = event.value) }
            }

            WeatherEvents.OnBack -> {
                emitEffect(WeatherEffects.NavigateToBack)
            }

            WeatherEvents.OnSubmit -> {
                viewModelScope.launch {
                    val lat = _state.value.latitude.toDoubleOrNull() ?: 0.0
                    val lon = _state.value.longitude.toDoubleOrNull() ?: 0.0

                    getWeatherUseCase.invoke(lat, lon).fold(
                        onSuccess = { weatherInfo ->
                            _state.update {
                                it.copy(
                                    temperature = weatherInfo.temperature.toString(),
                                    windspeed = weatherInfo.windspeed.toString(),
                                    winddirection = weatherInfo.winddirection.toString(),
                                    weathercode = weatherInfo.weathercode.toString(),
                                    time = weatherInfo.time
                                )
                            }
                        },
                        onFailure = {}
                    )
                }

            }
        }
    }

    private fun emitEffect(effect: WeatherEffects) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}