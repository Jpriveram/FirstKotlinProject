package edu.ucb.project.user_search.data.repository

import edu.ucb.project.user_search.data.datasource.GithubRemoteDataSource
import edu.ucb.project.user_search.data.mapper.toDomain
import edu.ucb.project.user_search.domain.model.UserInfoModel
import edu.ucb.project.user_search.domain.repository.GithubRepository

class GithubRepositoryImpl(val dataSource: GithubRemoteDataSource): GithubRepository {
    override suspend fun findByAlias(alias: String): Result<UserInfoModel> {
        return Result.success(dataSource.getUser(alias).toDomain())
    }
}