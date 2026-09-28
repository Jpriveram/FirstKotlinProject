package edu.ucb.project.register.presentation.state

data class RegisterState(
    val isLoading: Boolean = false,
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null
)

