package edu.ucb.project.exchangerate.presentation.viewmodel

import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel


data class ExchangeRateState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val list: List<ExchangeRateModel> = emptyList()
)