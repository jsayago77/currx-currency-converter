package com.jsayago77.currx.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsayago77.currx.data.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ExchangeRateRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun swapCurrencies() {
        _uiState.value = _uiState.value.copy(
            fromCurrency = _uiState.value.toCurrency,
            toCurrency = _uiState.value.fromCurrency
        )
        loadRate()
    }

    fun updateAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
        convert()
    }

    private fun loadRate() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            repository.getRate(_uiState.value.fromCurrency, _uiState.value.toCurrency)
                .onSuccess { rate ->
                    _uiState.value = _uiState.value.copy(
                        exchangeRate = rate,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
        }
    }

    private fun convert() {
        val amount = _uiState.value.amount.toDoubleOrNull() ?: 0.0
        _uiState.value = _uiState.value.copy(
            convertedAmount = String.format("%.2f", amount * _uiState.value.exchangeRate)
        )
    }
}

data class MainUiState(
    val amount: String = "1.00",
    val fromCurrency: String = "USD",
    val toCurrency: String = "EUR",
    val exchangeRate: Double = 0.0,
    val convertedAmount: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)