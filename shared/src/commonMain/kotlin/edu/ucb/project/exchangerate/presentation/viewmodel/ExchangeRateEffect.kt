package edu.ucb.project.exchangerate.presentation.viewmodel


interface ExchangeRateEffect {
    data class ShowToast(val message: String): ExchangeRateEffect
}