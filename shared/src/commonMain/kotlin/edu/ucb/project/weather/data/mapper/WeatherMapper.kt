package edu.ucb.project.weather.data.mapper

import edu.ucb.project.weather.data.dto.WeatherSearchDto
import edu.ucb.project.weather.domain.model.WeatherInfoModel

fun WeatherSearchDto.toDomain(): WeatherInfoModel = WeatherInfoModel(
    latitude = latitude ?: 0.0,
    longitude = longitude ?: 0.0,
    temperature = currentWeather?.temperature ?: 0.0,
    windspeed = currentWeather?.windspeed ?: 0.0,
    winddirection = currentWeather?.winddirection ?: 0.0,
    weathercode = currentWeather?.weathercode ?: 0,
    time = currentWeather?.time ?: ""
)