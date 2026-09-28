package edu.ucb.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherSearchDto(
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerialName("current_weather")
    val currentWeather: CurrentWeatherDto? = null
)

@Serializable
data class CurrentWeatherDto(
    val temperature: Double? = null,
    val windspeed: Double? = null,
    val winddirection: Double? = null,
    val weathercode: Int? = null,
    val time: String? = null
)