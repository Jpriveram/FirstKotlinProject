package edu.ucb.project.register.presentation.state
sealed interface RegisterEffect {
    data object NavigateToHome : RegisterEffect
    data object NavigateBackToLogin : RegisterEffect
    data class ShowToast(val message: String) : RegisterEffect
}