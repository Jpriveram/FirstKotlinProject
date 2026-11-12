package edu.ucb.project.biometric.domain.usecase

import edu.ucb.project.biometric.domain.repository.BiometricRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AuthenticateUserUseCaseTest {

    private class FakeBiometricRepository(
        var available: Boolean,
        var result: Boolean
    ) : BiometricRepository {

        var authenticateCalls = 0
        var lastFallback: Boolean? = null

        override suspend fun isBiometricAvailable(
            allowDeviceCredentialFallback: Boolean
        ): Boolean {
            lastFallback = allowDeviceCredentialFallback
            return available
        }

        override suspend fun authenticate(
            reason: String,
            allowDeviceCredentialFallback: Boolean
        ): Boolean {
            authenticateCalls++
            lastFallback = allowDeviceCredentialFallback
            return result
        }
    }

    @Test
    fun authenticationSucceeds() = runBlocking {
        val repository = FakeBiometricRepository(
            available = true,
            result = true
        )

        val useCase = AuthenticateUserUseCase(repository)

        val result = useCase.execute("Test")

        assertTrue(result)
        assertEquals(1, repository.authenticateCalls)
    }

    @Test
    fun authenticationUnavailable() = runBlocking {
        val repository = FakeBiometricRepository(
            available = false,
            result = true
        )

        val useCase = AuthenticateUserUseCase(repository)

        val result = useCase.execute("Test")

        assertFalse(result)
        assertEquals(0, repository.authenticateCalls)
    }

    @Test
    fun authenticationRejected() = runBlocking {
        val repository = FakeBiometricRepository(
            available = true,
            result = false
        )

        val useCase = AuthenticateUserUseCase(repository)

        val result = useCase.execute("Test")

        assertFalse(result)
    }

    @Test
    fun pinFallbackDisabledByDefault() = runBlocking {
        val repository = FakeBiometricRepository(
            available = true,
            result = true
        )

        val useCase = AuthenticateUserUseCase(repository)

        useCase.execute("Test")

        assertEquals(false, repository.lastFallback)
    }
}
