package edu.ucb.project.biometric.domain.repository

interface BiometricRepository {

    suspend fun isBiometricAvailable(
        allowDeviceCredentialFallback: Boolean
    ): Boolean

    suspend fun authenticate(
        reason: String,
        allowDeviceCredentialFallback: Boolean
    ): Boolean
}