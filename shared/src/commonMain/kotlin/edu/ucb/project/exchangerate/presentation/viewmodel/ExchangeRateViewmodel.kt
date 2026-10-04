package edu.ucb.project.exchangerate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.project.exchangerate.domain.model.ExchangeRateModel
import edu.ucb.project.exchangerate.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeRateViewModel(
    val repository: ExchangeRateRepository
): ViewModel() {

    private val _state = MutableStateFlow(ExchangeRateState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ExchangeRateEffect>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: ExchangeRateEvent) = viewModelScope.launch {
        when(event) {
            ExchangeRateEvent.OnAddRecord -> {
                repository.insert(ExchangeRateModel("a", "b"))
                val list = repository.getList()
                _state.update {
                    it.copy(list = list)
                }
            }
        }
    }
}