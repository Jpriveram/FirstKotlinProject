package edu.ucb.project.exchangerate.data.datasource

import edu.ucb.project.exchangerate.data.dao.ExchangeRateDao
import edu.ucb.project.exchangerate.data.entity.ExchangeRateEntity
import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExchangeRateLocalDataSource(
    val dao: ExchangeRateDao
) {
    fun getList(): Flow<List<ExchangeRateModel>> {
        return dao.getList().map { list ->
            list.map { it.toModel() }
        }
    }

    suspend fun insert(dollar: ExchangeRateModel) {
        dao.insert(dollar.toEntity())
    }

    private fun ExchangeRateEntity.toModel() : ExchangeRateModel {
        return ExchangeRateModel(
            official = dollarOfficial?: "",
            parallel = dollarParallel?:""
        )
    }

    private fun ExchangeRateModel.toEntity() = ExchangeRateEntity(
        dollarOfficial = official,
        dollarParallel = parallel
    )
}