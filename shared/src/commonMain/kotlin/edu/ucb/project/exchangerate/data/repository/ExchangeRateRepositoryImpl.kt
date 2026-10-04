package edu.ucb.project.exchangerate.data.repository

import edu.ucb.project.exchangerate.data.datasource.ExchangeRateLocalDataSource
import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel
import edu.ucb.project.exchangerate.domain.repository.ExchangeRateRepository


import kotlinx.coroutines.flow.Flow

class ExchangeRateRepositoryImpl(
    val localDataSource: ExchangeRateLocalDataSource
): ExchangeRateRepository {
    override fun getList(): Flow<List<ExchangeRateModel>> {
        return localDataSource.getList()
    }

    override suspend fun insert(dollar: ExchangeRateModel) {
        localDataSource.insert(dollar)
    }
}