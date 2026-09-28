package edu.ucb.project.login.presentation.viewmodel

sealed interface LoginEvent {
    data class OnUsernameChanged(val value: String) : LoginEvent
    data class OnPasswordChanged(val value: String) : LoginEvent
    data object OnLoginClick : LoginEvent
}