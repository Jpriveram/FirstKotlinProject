package edu.ucb.project.biometric.presentation.viewmodel

data class BiometricState(
    val isAvailable: Boolean = false,
    val isChecking: Boolean = true,
    val isAuthenticating: Boolean = false,
    val statusMessage: String = "Comprobando biometría...",
    val isAuthenticated: Boolean = false
)
