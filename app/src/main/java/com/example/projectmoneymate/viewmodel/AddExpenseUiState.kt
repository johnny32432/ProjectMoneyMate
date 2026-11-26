package com.example.projectmoneymate.viewmodel

import com.example.projectmoneymate.data.Category

data class AddExpenseUiState(
    val title: String = "",
    val amount: String = "",
    val note: String = "",
    val category: Category = Category.FOOD,
    val titleError: String? = null,
    val amountError: String? = null,
    val isEditMode: Boolean = false
)