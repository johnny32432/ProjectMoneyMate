package com.example.projectmoneymate.viewmodel

import com.example.projectmoneymate.data.CurrencyRate

sealed interface RatesUiState {
    data object Loading : RatesUiState

    data class Success(
        val rates: List<CurrencyRate>,
        val updated: String
    ) : RatesUiState

    data class Error(
        val message: String
    ) : RatesUiState

    data object Empty : RatesUiState
}