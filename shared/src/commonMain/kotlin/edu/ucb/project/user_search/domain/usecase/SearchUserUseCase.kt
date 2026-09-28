package edu.ucb.project.user_search.domain.usecase

import edu.ucb.project.user_search.domain.model.UserInfoModel
import edu.ucb.project.user_search.domain.repository.GithubRepository

class SearchUserUseCase(val repository: GithubRepository){
    suspend fun invoke (alias : String): Result<UserInfoModel> {
        return repository.findByAlias(alias)
    }
}
