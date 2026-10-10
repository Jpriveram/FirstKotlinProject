package edu.ucb.project.exchangerate.data.repository

import kotlinx.coroutines.flow.Flow
import edu.ucb.project.exchangerate.data.datasource.RealTimeDataBase
import edu.ucb.project.exchangerate.domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(
    val realTimeDataBase: RealTimeDataBase
): ExchangeRepository {
    override suspend fun observe(): Flow<String?> {
        return realTimeDataBase.observeMessage()
    }
}