package edu.ucb.project.login.domain.repository

import edu.ucb.project.login.domain.model.User
import edu.ucb.project.login.domain.vo.Password
import edu.ucb.project.login.domain.vo.Email

interface LoginRepository {
    suspend fun login(username: Email, password: Password): Result<User>
}