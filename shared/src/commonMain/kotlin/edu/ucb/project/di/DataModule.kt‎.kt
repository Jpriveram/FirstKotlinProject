package edu.ucb.project.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import edu.ucb.project.config.AppDatabase
import edu.ucb.project.exchangerate.data.dao.ExchangeRateDao
import edu.ucb.project.exchangerate.data.datasource.ExchangeRateLocalDataSource
import edu.ucb.project.exchangerate.data.datasource.RealTimeDataBase
import edu.ucb.project.exchangerate.data.repository.ExchangeRateRepositoryImpl
import edu.ucb.project.exchangerate.data.repository.ExchangeRepositoryImpl
import edu.ucb.project.exchangerate.domain.repository.ExchangeRateRepository
import edu.ucb.project.exchangerate.domain.repository.ExchangeRepository
import edu.ucb.project.user_search.data.datasource.GithubRemoteDataSource
import edu.ucb.project.user_search.domain.repository.GithubRepository
import edu.ucb.project.user_search.data.repository.GithubRepositoryImpl
import edu.ucb.project.user_search.data.service.GitHubApiService
import edu.ucb.project.weather.data.datasource.WeatherRemoteDataSource
import edu.ucb.project.weather.data.service.WeatherApiService
import edu.ucb.project.weather.domain.repository.WeatherRepository
import edu.ucb.project.weather.data.repository.WeatherRepositoryImpl
import kotlin.math.sin

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }
    single<WeatherRemoteDataSource> { WeatherApiService() }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single<ExchangeRateDao>{
        get<AppDatabase>().getDao()

    }
    singleOf(::ExchangeRateLocalDataSource)
    single<ExchangeRateRepository> { ExchangeRateRepositoryImpl(get()) }

    singleOf(::RealTimeDataBase)
    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }
}

