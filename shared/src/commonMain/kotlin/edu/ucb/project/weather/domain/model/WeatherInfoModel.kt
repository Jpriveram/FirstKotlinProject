package edu.ucb.project.weather.domain.model

data class WeatherInfoModel(
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Double,
    val weathercode: Int,
    val time: String
)