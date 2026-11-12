package edu.ucb.project.biometric.data.repository

import edu.ucb.project.biometric.domain.repository.BiometricRepository
import eu.anifantakis.lib.ksafe.biometrics.KSafeBiometrics

class KSafeBiometricRepository : BiometricRepository {

    override suspend fun isBiometricAvailable(
        allowDeviceCredentialFallback: Boolean
    ): Boolean {
        return KSafeBiometrics.biometricsAvailable(
            allowDeviceCredentialFallback =
                allowDeviceCredentialFallback
        )
    }

    override suspend fun authenticate(
        reason: String,
        allowDeviceCredentialFallback: Boolean
    ): Boolean {
        return KSafeBiometrics.verifyBiometric(
            reason = reason,
            title = "Autenticación de seguridad",
            allowDeviceCredentialFallback =
                allowDeviceCredentialFallback
        )
    }
}