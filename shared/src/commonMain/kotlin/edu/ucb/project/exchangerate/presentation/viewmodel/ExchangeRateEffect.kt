package edu.ucb.project.exchangerate.presentation.viewmodel


sealed interface ExchangeRateEffect {
    data class ShowToast(val message: String): ExchangeRateEffect
    object NavigateToAddScreen: ExchangeRateEffect
    object NavigateBack: ExchangeRateEffect
}
