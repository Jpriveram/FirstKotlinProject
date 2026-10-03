package edu.ucb.project.exchangerate.data.datasource

import edu.ucb.project.exchangerate.data.dao.ExchangeRateDao
import edu.ucb.project.exchangerate.data.entity.ExchangeRateEntity

class ExchangeRateLocalDataSource(
    val dao: ExchangeRateDao
) {
    suspend fun getList(): List<ExchangeRateModel> {
        return dao.getList().map {
            it.toModel()
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
        dollarOfficial = "12.05",
        dollarParallel = "12.07"
    )
}