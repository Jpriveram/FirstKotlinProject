package edu.ucb.project.weather.presentation.viewmodel

sealed interface WeatherEvents {
    data class OnLatitudeChange(val value: String) : WeatherEvents
    data class OnLongitudeChange(val value: String) : WeatherEvents
    data object OnSubmit : WeatherEvents
    data object OnBack : WeatherEvents
}