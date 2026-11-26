package com.example.projectmoneymate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmoneymate.data.CurrencyRate
import com.example.projectmoneymate.data.RatesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RatesViewModel : ViewModel() {

    private val repository = RatesRepository()

    private val _uiState = MutableStateFlow<RatesUiState>(RatesUiState.Loading)
    val uiState: StateFlow<RatesUiState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private var allRates: List<CurrencyRate> = emptyList()
    private var updated: String = ""

    init {
        loadRates()
    }

    fun loadRates() {
        viewModelScope.launch {
            _uiState.value = RatesUiState.Loading
            try {
                val result = repository.getRates()
                allRates = result.rates
                updated = result.updated
                applyFilter()
            } catch (e: IOException) {
                _uiState.value = RatesUiState.Error(
                    "No internet connection. Check your network and try again."
                )
            } catch (e: HttpException) {
                _uiState.value = RatesUiState.Error(
                    "Server error: ${e.code()}"
                )
            }
        }
    }

    fun onQueryChange(value: String) {
        _query.value = value
        if (_uiState.value !is RatesUiState.Loading &&
            _uiState.value !is RatesUiState.Error
        ) {
            applyFilter()
        }
    }

    private fun applyFilter() {
        val q = _query.value.trim()

        val filtered = allRates.filter {
            q.isEmpty() ||
                    it.code.contains(q, ignoreCase = true) ||
                    it.name.contains(q, ignoreCase = true)
        }

        _uiState.value =
            if (filtered.isEmpty()) RatesUiState.Empty
            else RatesUiState.Success(filtered, updated)
    }
}