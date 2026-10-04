package edu.ucb.project.exchangerate.domain.repository


import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel

import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    fun getList(): Flow<List<ExchangeRateModel>>

    suspend fun insert(dollar: ExchangeRateModel)
}