package edu.ucb.project.weather.data.repository

import edu.ucb.project.weather.data.datasource.WeatherRemoteDataSource
import edu.ucb.project.weather.data.mapper.toDomain
import edu.ucb.project.weather.domain.model.WeatherInfoModel
import edu.ucb.project.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(val dataSource: WeatherRemoteDataSource) : WeatherRepository {
    override suspend fun getWeather(latitude: Double, longitude: Double): Result<WeatherInfoModel> {
        return Result.success(dataSource.getWeather(latitude, longitude).toDomain())
    }
}