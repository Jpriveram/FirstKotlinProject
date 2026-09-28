package edu.ucb.project.register.domain.usecase
import edu.ucb.project.register.domain.model.RegisteredUser
import edu.ucb.project.register.domain.repository.RegisterRepository
import edu.ucb.project.register.domain.vo.Email
import edu.ucb.project.register.domain.vo.FullName
import edu.ucb.project.register.domain.vo.Password

class RegisterUseCase(private val repository: RegisterRepository) {
    suspend operator fun invoke(
        fullName: FullName,
        email: Email,
        password: Password
    ): Result<RegisteredUser> {
        return repository.register(fullName, email, password)
    }
}