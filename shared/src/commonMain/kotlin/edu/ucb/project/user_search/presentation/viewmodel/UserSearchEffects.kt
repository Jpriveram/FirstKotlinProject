package edu.ucb.project.user_search.presentation.viewmodel

sealed interface UserSearchEffects {
    data class ShowToast(val message: String): UserSearchEffects
    object NavigateToBack: UserSearchEffects
}