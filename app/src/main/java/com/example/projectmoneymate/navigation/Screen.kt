package com.example.projectmoneymate.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object History : Screen("history")
    data object AddExpense : Screen("add_expense")
    data object Rates : Screen("rates")
    data object Settings : Screen("settings")

    data object Details : Screen("details/{expenseId}") {
        const val ARG_ID = "expenseId"
        fun createRoute(expenseId: Int) = "details/$expenseId"
    }

    data object EditExpense : Screen("edit_expense/{expenseId}") {
        const val ARG_ID = "expenseId"
        fun createRoute(expenseId: Int) = "edit_expense/$expenseId"
    }
}