package edu.ucb.project.exchangerate.presentation.viewmodel

sealed interface ExchangeRateEvent {
    object OnAddRecord: ExchangeRateEvent
}