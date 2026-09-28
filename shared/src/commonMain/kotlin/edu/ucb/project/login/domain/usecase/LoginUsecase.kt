package edu.ucb.project.login.domain.usecase
import edu.ucb.project.login.domain.model.User
import edu.ucb.project.login.domain.repository.LoginRepository
import edu.ucb.project.login.domain.vo.Password
import edu.ucb.project.login.domain.vo.Email

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(username: Email, password: Password): Result<User> {
        return repository.login(username, password)
    }
}