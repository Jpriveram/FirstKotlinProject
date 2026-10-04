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

    init {
        viewModelScope.launch {
            repository.getList().collect { list ->
                _state.update { it.copy(list = list) }
            }
        }
    }

    fun emitEvent(event: ExchangeRateEvent) = viewModelScope.launch {
        when(event) {
            ExchangeRateEvent.LoadExchangeRates -> {
                // Handled in init block now
            }
            ExchangeRateEvent.OnAddExchangeRateClick -> {
                _state.update { it.copy(officialRateInput = "", parallelRateInput = "") }
                _effect.emit(ExchangeRateEffect.NavigateToAddScreen)
            }
            is ExchangeRateEvent.OnOfficialRateChanged -> {
                _state.update { it.copy(officialRateInput = sanitizeInput(event.rate)) }
            }
            is ExchangeRateEvent.OnParallelRateChanged -> {
                _state.update { it.copy(parallelRateInput = sanitizeInput(event.rate)) }
            }
            ExchangeRateEvent.OnSaveExchangeRate -> {
                val currentOfficial = _state.value.officialRateInput
                val currentParallel = _state.value.parallelRateInput
                if (currentOfficial.isNotBlank() && currentParallel.isNotBlank()) {
                    repository.insert(ExchangeRateModel(currentOfficial, currentParallel))
                    _state.update { it.copy(officialRateInput = "", parallelRateInput = "") }
                    _effect.emit(ExchangeRateEffect.NavigateBack)
                } else {
                    _effect.emit(ExchangeRateEffect.ShowToast("Los campos no pueden estar vacíos"))
                }
            }
            ExchangeRateEvent.OnBack -> {
                _effect.emit(ExchangeRateEffect.NavigateBack)
            }
        }
    }

    private fun sanitizeInput(input: String): String {
        // Reemplaza comas por puntos
        val dotInput = input.replace(",", ".")

        // Filtra para dejar solo números y como máximo un punto decimal
        var hasDot = false
        return dotInput.filter { char ->
            if (char == '.') {
                if (hasDot) {
                    false // Si ya hay un punto, ignoramos los siguientes
                } else {
                    hasDot = true
                    true
                }
            } else {
                char.isDigit()
            }
        }
    }
}
