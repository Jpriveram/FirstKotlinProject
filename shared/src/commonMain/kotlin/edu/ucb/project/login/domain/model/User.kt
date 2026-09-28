package edu.ucb.project.login.domain.model
data class User(
    val username: String,
    val token: String? = null
)