package edu.ucb.project.user_search.data.datasource

import edu.ucb.project.user_search.data.dto.UserSearchDto

interface GithubRemoteDataSource {
    suspend fun getUser(nickname: String) : UserSearchDto
}