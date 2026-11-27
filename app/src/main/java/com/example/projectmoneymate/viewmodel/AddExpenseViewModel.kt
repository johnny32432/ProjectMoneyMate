package com.example.projectmoneymate.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.projectmoneymate.data.Category
import com.example.projectmoneymate.data.Expense
import com.example.projectmoneymate.data.ExpenseRepository
import com.example.projectmoneymate.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddExpenseViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val editingExpense: Expense? =
        savedStateHandle.get<Int>(Screen.EditExpense.ARG_ID)?.let { ExpenseRepository.getById(it) }

    private val _uiState = MutableStateFlow(initialState())
    val uiState: StateFlow<AddExpenseUiState> = _uiState.asStateFlow()

    private fun initialState(): AddExpenseUiState {
        val expense = editingExpense ?: return AddExpenseUiState()
        return AddExpenseUiState(
            title = expense.title,
            amount = amountToText(expense.amount),
            note = expense.note,
            category = expense.category,
            isEditMode = true
        )
    }

    private fun amountToText(amount: Double): String =
        if (amount % 1.0 == 0.0) amount.toLong().toString() else amount.toString()

    fun onTitleChange(value: String) {
        _uiState.update { it.copy(title = value, titleError = null) }
    }

    fun onAmountChange(value: String) {
        val normalized = value.replace(',', '.')
        if (normalized.isEmpty() || normalized.matches(Regex("^\\d{0,9}(\\.\\d{0,2})?$"))) {
            _uiState.update { it.copy(amount = normalized, amountError = null) }
        }
    }

    fun onNoteChange(value: String) {
        _uiState.update { it.copy(note = value) }
    }

    fun onCategoryChange(category: Category) {
        _uiState.update { it.copy(category = category) }
    }

    fun save(): Boolean {
        val state = _uiState.value
        val amount = state.amount.toDoubleOrNull()

        val titleError = if (state.title.isBlank()) "Enter a title" else null
        val amountError = if (amount == null || amount <= 0) "Enter an amount greater than 0" else null

        if (titleError != null || amountError != null || amount == null) {
            _uiState.update { it.copy(titleError = titleError, amountError = amountError) }
            return false
        }

        val existing = editingExpense
        if (existing != null) {
            ExpenseRepository.update(
                existing.copy(
                    title = state.title.trim(),
                    amount = amount,
                    category = state.category,
                    note = state.note.trim()
                )
            )
        } else {
            ExpenseRepository.add(
                title = state.title.trim(),
                amount = amount,
                category = state.category,
                note = state.note.trim()
            )
        }
        return true
    }
}