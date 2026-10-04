package edu.ucb.project.exchangerate.presentation.viewmodel

sealed interface ExchangeRateEvent {
    object LoadExchangeRates: ExchangeRateEvent
    object OnAddExchangeRateClick: ExchangeRateEvent
    data class OnOfficialRateChanged(val rate: String): ExchangeRateEvent
    data class OnParallelRateChanged(val rate: String): ExchangeRateEvent
    object OnSaveExchangeRate: ExchangeRateEvent
    object OnBack: ExchangeRateEvent
}
