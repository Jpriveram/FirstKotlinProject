package edu.ucb.project.user_search.presentation.viewmodel

data class UserSearchState(
    val alias: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String? = null,
    val company: String? = null,
    val avatarUrl: String? = null
)