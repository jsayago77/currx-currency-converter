package com.jsayago77.currx.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsayago77.currx.data.model.RateOption
import com.jsayago77.currx.data.remote.dto.CurrenciesResponse
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

    fun getCurrencies() {
        viewModelScope.launch {
            repository.getCurrencies()
                .onSuccess { currencies ->
                    _uiState.value = _uiState.value.copy(currencies = currencies)
                }
                .onFailure {  e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
        }
    }

    fun swapCurrencies() {
        val current = _uiState.value
        _uiState.value = current.copy(
            fromCurrency = current.toCurrency,
            toCurrency = current.fromCurrency,
            amount = current.convertedAmount,
            selectedRateIndex = 0
        )
        loadRate()
        convert()
    }

    fun updateAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
        convert()
    }

    fun updateFromCurrency(currency: String) {
        _uiState.value = _uiState.value.copy(
            fromCurrency = currency,
            selectedRateIndex = 0
        )
        loadRate()
    }

    fun updateToCurrency(currency: String) {
        _uiState.value = _uiState.value.copy(
            toCurrency = currency,
            selectedRateIndex = 0
        )
        loadRate()
    }

    fun selectRateOption(index: Int) {
        _uiState.value = _uiState.value.copy(selectedRateIndex = index)
        convert()
    }

    private fun loadRate() {
        viewModelScope.launch {
            val state = _uiState.value
            _uiState.value = state.copy(isLoading = true)

            repository.getRate(state.fromCurrency, state.toCurrency)
                .onSuccess { rates ->
                    val defaultIndex = if (rates.size > 1) {
                        // Default to first LATAM rate (non-interbank)
                        val latamIndex = rates.indexOfFirst { it.source != "Frankfurther" }
                        if (latamIndex >= 0) latamIndex else 0
                    } else 0

                    _uiState.value = _uiState.value.copy(
                        rateOptions = rates,
                        selectedRateIndex = defaultIndex.coerceIn(0, rates.lastIndex),
                        isLoading = false,
                        error = null
                    )
                    convert()
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
        val state = _uiState.value
        val amount = state.amount.toDoubleOrNull() ?: 0.0
        val rate = if (state.rateOptions.isNotEmpty()) {
            val index = state.selectedRateIndex.coerceIn(0, state.rateOptions.lastIndex)
            state.rateOptions[index].rate
        } else 1.0

        _uiState.value = state.copy(
            exchangeRate = rate,
            convertedAmount = String.format("%.4f", amount * rate)
        )
    }
}

data class MainUiState(
    val amount: String = "1.00",
    val fromCurrency: String = "VES",
    val toCurrency: String = "USD",
    val exchangeRate: Double = 1.00,
    val convertedAmount: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val currencies: List<CurrenciesResponse> = emptyList(),
    val rateOptions: List<RateOption> = emptyList(),
    val selectedRateIndex: Int = 0
)
