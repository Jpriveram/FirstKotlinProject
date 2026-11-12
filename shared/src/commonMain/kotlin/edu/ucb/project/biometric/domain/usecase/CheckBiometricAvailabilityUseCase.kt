package edu.ucb.project.biometric.domain.usecase

import edu.ucb.project.biometric.domain.repository.BiometricRepository

class CheckBiometricAvailabilityUseCase(
    private val repository: BiometricRepository
) {
    suspend fun execute(
        allowDeviceCredentialFallback: Boolean = false
    ): Boolean {
        return repository.isBiometricAvailable(
            allowDeviceCredentialFallback
        )
    }
}
