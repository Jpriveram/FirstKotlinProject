package edu.ucb.project.exchangerate.domain.repository


import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel

interface ExchangeRateRepository {
    suspend fun getList(): List<ExchangeRateModel>

    suspend fun insert(dollar: ExchangeRateModel)
}