package edu.ucb.project.weather.presentation.viewmodel

data class WeatherState(
    val latitude: String = "40.71",
    val longitude: String = "-74.01",
    val temperature: String = "",
    val windspeed: String = "",
    val winddirection: String = "",
    val weathercode: String = "",
    val time: String = ""
)