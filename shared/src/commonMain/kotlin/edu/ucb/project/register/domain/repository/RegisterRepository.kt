package edu.ucb.project.register.domain.repository
import edu.ucb.project.register.domain.model.RegisteredUser
import edu.ucb.project.register.domain.vo.Email
import edu.ucb.project.register.domain.vo.FullName
import edu.ucb.project.register.domain.vo.Password

interface RegisterRepository {
    suspend fun register(
        fullName: FullName,
        email: Email,
        password: Password
    ): Result<RegisteredUser>
}