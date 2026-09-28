package edu.ucb.project.user_search.domain.repository

import edu.ucb.project.user_search.domain.model.UserInfoModel

interface GithubRepository {
    suspend fun findByAlias(alias: String): Result<UserInfoModel>
}