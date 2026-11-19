package com.example.projectmoneymate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmoneymate.data.Category
import com.example.projectmoneymate.data.Expense
import com.example.projectmoneymate.data.ExpenseRepository
import com.example.projectmoneymate.util.formatDayHeader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel : ViewModel() {

    private val selectedCategory = MutableStateFlow<Category?>(null)

    val uiState: StateFlow<HistoryUiState> =
        combine(ExpenseRepository.expenses, selectedCategory) { expenses, category ->
            buildState(expenses, category)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = buildState(ExpenseRepository.expenses.value, null)
        )

    fun selectCategory(category: Category?) {
        selectedCategory.value = category
    }

    private fun buildState(expenses: List<Expense>, category: Category?): HistoryUiState {
        val filtered = expenses
            .filter { category == null || it.category == category }
            .sortedByDescending { it.date }

        val groups = filtered
            .groupBy { formatDayHeader(it.date) }
            .map { (header, list) -> DayGroup(header, list, list.sumOf { it.amount }) }

        return HistoryUiState(
            groups = groups,
            selectedCategory = category,
            total = filtered.sumOf { it.amount }
        )
    }
}