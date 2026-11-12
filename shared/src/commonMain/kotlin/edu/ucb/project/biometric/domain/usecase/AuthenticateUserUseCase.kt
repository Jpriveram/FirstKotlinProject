package edu.ucb.project.biometric.domain.usecase

import edu.ucb.project.biometric.domain.repository.BiometricRepository

class AuthenticateUserUseCase(
    private val repository: BiometricRepository
) {
    suspend fun execute(
        reason: String,
        allowDeviceCredentialFallback: Boolean = false
    ): Boolean {

        if (!repository.isBiometricAvailable(
                allowDeviceCredentialFallback
            )
        ) {
            return false
        }

        return repository.authenticate(
            reason,
            allowDeviceCredentialFallback
        )
    }
}