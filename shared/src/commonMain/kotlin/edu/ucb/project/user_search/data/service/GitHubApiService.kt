package edu.ucb.project.user_search.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import edu.ucb.project.user_search.data.datasource.GithubRemoteDataSource
import edu.ucb.project.user_search.data.dto.UserSearchDto

class GitHubApiService : GithubRemoteDataSource {
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

    override suspend fun getUser(nickname: String): UserSearchDto {
        val response = client.get("https://api.github.com/users/$nickname")
        try {
            return response.body<UserSearchDto>()
        } catch (e: Exception) {
            throw e
        }
    }
}