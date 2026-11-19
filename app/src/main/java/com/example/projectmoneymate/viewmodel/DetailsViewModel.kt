package com.example.projectmoneymate.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmoneymate.data.Expense
import com.example.projectmoneymate.data.ExpenseRepository
import com.example.projectmoneymate.navigation.Screen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val expenseId: Int = checkNotNull(savedStateHandle.get<Int>(Screen.Details.ARG_ID))

    val expense: StateFlow<Expense?> = ExpenseRepository.expenses
        .map { list -> list.find { it.id == expenseId } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExpenseRepository.getById(expenseId)
        )

    fun delete() {
        ExpenseRepository.delete(expenseId)
    }
}