package edu.ucb.project.exchangerate.domain.usecase


import kotlinx.coroutines.flow.Flow
import edu.ucb.project.exchangerate.domain.repository.ExchangeRepository

class ObserveExchangeUseCase(
    val repository: ExchangeRepository
) {
    suspend fun invoke(): Flow<String?> {
        return repository.observe()
    }
}