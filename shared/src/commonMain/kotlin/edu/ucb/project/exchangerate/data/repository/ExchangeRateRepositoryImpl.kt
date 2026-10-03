package edu.ucb.project.exchangerate.data.repository

import edu.ucb.project.exchangerate.data.datasource.ExchangeRateLocalDataSource
import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel
import edu.ucb.project.exchangerate.domain.repository.ExchangeRateRepository


class DollarRepositoryImpl(
    val localDataSource: ExchangeRateLocalDataSource
): ExchangeRateRepository {
    override suspend fun getList(): List<ExchangeRateModel> {
        return localDataSource.getList()
    }

    override suspend fun insert(dollar: ExchangeRateModel) {
        localDataSource.insert(dollar)
    }
}