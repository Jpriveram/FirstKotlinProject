package edu.ucb.project.user_search.presentation.viewmodel

sealed interface UserSearchEvents{
    object OnBack: UserSearchEvents
    object OnSubmit: UserSearchEvents
    data class OnAliasChange(val value: String): UserSearchEvents

}