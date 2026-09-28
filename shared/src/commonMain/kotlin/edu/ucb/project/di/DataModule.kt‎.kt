package edu.ucb.project.di

import edu.ucb.project.user_search.data.datasource.GithubRemoteDataSource
import org.koin.dsl.module
import edu.ucb.project.user_search.domain.repository.GithubRepository
import edu.ucb.project.user_search.data.repository.GithubRepositoryImpl
import edu.ucb.project.user_search.data.service.GitHubApiService


val dataModule = module {
    single<GithubRemoteDataSource>{ GitHubApiService() }
    single<GithubRepository>{ GithubRepositoryImpl(get()) }
}