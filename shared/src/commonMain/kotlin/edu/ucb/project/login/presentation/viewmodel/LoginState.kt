package edu.ucb.project.login.presentation.viewmodel

data class LoginState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val errorMessage: String? = null
)