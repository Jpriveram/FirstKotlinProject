package edu.ucb.project.di

import edu.ucb.project.user_search.data.datasource.GithubRemoteDataSource
import org.koin.dsl.module
import edu.ucb.project.user_search.domain.repository.GithubRepository
import edu.ucb.project.user_search.data.repository.GithubRepositoryImpl
import edu.ucb.project.user_search.data.service.GitHubApiService
import edu.ucb.project.weather.data.datasource.WeatherRemoteDataSource
import edu.ucb.project.weather.data.service.WeatherApiService
import edu.ucb.project.weather.domain.repository.WeatherRepository
import edu.ucb.project.weather.data.repository.WeatherRepositoryImpl
import edu.ucb.project.weather.domain.usecase.GetWeatherUseCase
import edu.ucb.project.weather.presentation.viewmodel.WeatherViewModel


val dataModule = module {
    single<GithubRemoteDataSource>{ GitHubApiService() }
    single<GithubRepository>{ GithubRepositoryImpl(get()) }
    single<WeatherRemoteDataSource>{ WeatherApiService() }
    single<WeatherRepository>{ WeatherRepositoryImpl(get())}
    single { GetWeatherUseCase(get()) }
}