package edu.ucb.project.exchangerate.domain.repository

import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    suspend fun observe(): Flow<String?>
}