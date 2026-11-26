package com.example.projectmoneymate.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun EditExpenseScreen(onBack: () -> Unit, onSaved: () -> Unit) {
    AddExpenseScreen(onBack = onBack, onSaved = onSaved)
}