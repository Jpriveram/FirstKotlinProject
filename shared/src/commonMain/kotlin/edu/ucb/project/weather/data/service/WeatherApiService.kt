package edu.ucb.project.weather.data.service

import edu.ucb.project.weather.data.datasource.WeatherRemoteDataSource
import edu.ucb.project.weather.data.dto.WeatherSearchDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class WeatherApiService : WeatherRemoteDataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherSearchDto {
        val url = "https://api.open-meteo.com/v1/forecast?latitude=$latitude&longitude=$longitude&current_weather=true"
        val response = client.get(url)
        try {
            return response.body<WeatherSearchDto>()
        } catch (e: Exception) {
            throw e
        }
    }
}