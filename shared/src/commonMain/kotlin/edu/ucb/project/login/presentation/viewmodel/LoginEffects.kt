package edu.ucb.project.login.presentation.viewmodel
sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data class ShowToast(val message: String) : LoginEffect
}