package com.example.projectmoneymate.viewmodel

import com.example.projectmoneymate.data.Category
import com.example.projectmoneymate.data.Expense
data class CategoryTotal(
    val category: Category,
    val total: Double,
    val percent: Float
)

data class DashboardUiState(
    val total: Double = 0.0,
    val count: Int = 0,
    val byCategory: List<CategoryTotal> = emptyList(),
    val recent: List<Expense> = emptyList()
)