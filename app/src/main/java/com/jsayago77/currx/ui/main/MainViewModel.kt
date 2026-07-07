package com.jsayago77.currx.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsayago77.currx.data.remote.dto.RateResponse
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
                .onSuccess {

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
        _uiState.value = _uiState.value.copy(
            fromCurrency = _uiState.value.toCurrency,
            toCurrency = _uiState.value.fromCurrency,
            amount = _uiState.value.convertedAmount
        )
        loadRate()
        convert()
    }

    fun updateAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
        convert()
    }

    private fun loadRate() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            repository.getDollarRate("ve")
                .onSuccess { rate ->
                    _uiState.value = _uiState.value.copy(
                        exchangeRate = rate as Double,
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
    val fromCurrency: String = "VES",
    val toCurrency: String = "USD",
    val exchangeRate: Double = 1.00,
    val convertedAmount: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)