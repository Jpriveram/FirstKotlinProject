package edu.ucb.project.weather.data.datasource

import edu.ucb.project.weather.data.dto.WeatherSearchDto

interface WeatherRemoteDataSource {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherSearchDto
}