package edu.ucb.project.biometric.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.biometric.domain.usecase.AuthenticateUserUseCase
import edu.ucb.project.biometric.domain.usecase.CheckBiometricAvailabilityUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BiometricViewModel(
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val checkAvailabilityUseCase: CheckBiometricAvailabilityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BiometricState())
    val state = _state.asStateFlow()

    // Política temporal: solo biometría, sin PIN
    private val allowPinFallback = false

    init {
        checkBiometricAvailability()
    }

    fun checkBiometricAvailability() {
        if (_state.value.isChecking &&
            _state.value.statusMessage != "Comprobando biometría..."
        ) return

        _state.update {
            it.copy(
                isChecking = true,
                isAuthenticated = false,
                statusMessage = "Comprobando biometría..."
            )
        }

        viewModelScope.launch {
            try {
                val available = checkAvailabilityUseCase.execute(
                    allowDeviceCredentialFallback = allowPinFallback
                )

                _state.update {
                    it.copy(
                        isAvailable = available,
                        isChecking = false,
                        statusMessage = if (available) {
                            "Biometría disponible"
                        } else {
                            "Biometría no disponible o no configurada"
                        }
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isAvailable = false,
                        isChecking = false,
                        statusMessage = "Error comprobando biometría"
                    )
                }
            }
        }
    }

    fun authenticate() {
        val current = _state.value

        if (!current.isAvailable ||
            current.isChecking ||
            current.isAuthenticating
        ) return

        _state.update {
            it.copy(
                isAuthenticating = true,
                isAuthenticated = false,
                statusMessage = "Esperando autenticación..."
            )
        }

        viewModelScope.launch {
            try {
                val authenticated = authenticateUserUseCase.execute(
                    reason = "Verifica tu identidad",
                    allowDeviceCredentialFallback = allowPinFallback
                )

                _state.update {
                    it.copy(
                        isAuthenticating = false,
                        isAuthenticated = authenticated,
                        statusMessage = if (authenticated) {
                            "Autenticación exitosa"
                        } else {
                            "Autenticación cancelada o rechazada"
                        }
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isAuthenticating = false,
                        isAuthenticated = false,
                        statusMessage = "Error durante la autenticación"
                    )
                }
            }
        }
    }
}
