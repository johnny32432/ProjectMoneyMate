package com.example.projectmoneymate.viewmodel

import com.example.projectmoneymate.data.Category
import com.example.projectmoneymate.data.Expense

data class DayGroup(
    val header: String,
    val expenses: List<Expense>,
    val total: Double
)

data class HistoryUiState(
    val groups: List<DayGroup> = emptyList(),
    val selectedCategory: Category? = null,
    val total: Double = 0.0
)