package com.example.projectmoneymate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmoneymate.data.Expense
import com.example.projectmoneymate.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = ExpenseRepository.expenses
        .map { expenses -> buildState(expenses) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = buildState(ExpenseRepository.expenses.value)
        )

    private fun buildState(expenses: List<Expense>): DashboardUiState {
        val total = expenses.sumOf { it.amount }

        val byCategory = expenses
            .groupBy { it.category }
            .map { (category, list) ->
                val sum = list.sumOf { it.amount }
                CategoryTotal(
                    category = category,
                    total = sum,
                    percent = if (total > 0) (sum / total).toFloat() else 0f
                )
            }
            .sortedByDescending { it.total }

        return DashboardUiState(
            total = total,
            count = expenses.size,
            byCategory = byCategory,
            recent = expenses.sortedByDescending { it.date }.take(5)
        )
    }
}