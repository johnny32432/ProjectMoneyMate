package com.example.projectmoneymate.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object History : Screen("history")
    data object AddExpense : Screen("add_expense")

    // Экран деталей принимает аргумент expenseId
    data object Details : Screen("details/{expenseId}") {
        const val ARG_ID = "expenseId"
        fun createRoute(expenseId: Int) = "details/$expenseId"
    }
}