package com.example.projectmoneymate.data.local

import com.example.projectmoneymate.data.Category

object SampleData {

    private const val HOUR = 60 * 60 * 1000L
    private const val DAY = 24 * HOUR

    fun expenses(): List<ExpenseEntity> {
        val now = System.currentTimeMillis()
        return listOf(
            ExpenseEntity(title = "Lunch at cafe", amount = 3500.0, category = Category.FOOD.name, date = now - 2 * HOUR, note = "Business lunch"),
            ExpenseEntity(title = "Taxi to university", amount = 1800.0, category = Category.TRANSPORT.name, date = now - 5 * HOUR, note = ""),
            ExpenseEntity(title = "Groceries", amount = 12400.0, category = Category.FOOD.name, date = now - DAY, note = "Magnum"),
            ExpenseEntity(title = "Cinema", amount = 4000.0, category = Category.FUN.name, date = now - DAY - 3 * HOUR, note = "Two tickets"),
            ExpenseEntity(title = "Internet", amount = 7990.0, category = Category.BILLS.name, date = now - 2 * DAY, note = ""),
            ExpenseEntity(title = "Sneakers", amount = 32000.0, category = Category.SHOPPING.name, date = now - 3 * DAY, note = "")
        )
    }
}