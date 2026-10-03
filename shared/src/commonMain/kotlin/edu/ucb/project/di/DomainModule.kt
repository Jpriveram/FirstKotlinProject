package edu.ucb.project.di

import edu.ucb.project.login.domain.usecase.LoginUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import edu.ucb.project.user_search.domain.usecase.SearchUserUseCase
import edu.ucb.project.weather.domain.usecase.GetWeatherUseCase

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::SearchUserUseCase)
    singleOf(::GetWeatherUseCase)
}
