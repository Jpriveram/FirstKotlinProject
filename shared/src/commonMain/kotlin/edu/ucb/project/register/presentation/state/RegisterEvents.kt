package edu.ucb.project.register.presentation.state

sealed interface RegisterEvent {
    data class OnFullNameChanged(val value: String) : RegisterEvent
    data class OnEmailChanged(val value: String) : RegisterEvent
    data class OnPasswordChanged(val value: String) : RegisterEvent
    data class OnConfirmPasswordChanged(val value: String) : RegisterEvent
    data object OnRegisterClick : RegisterEvent
    data object OnLoginLinkClick : RegisterEvent
}