package com.example.projectmoneymate.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


object ExpenseRepository {

    private const val HOUR = 60 * 60 * 1000L
    private const val DAY = 24 * HOUR
    private val now = System.currentTimeMillis()

    private val _expenses = MutableStateFlow(
        listOf(
            Expense(1, "Lunch at cafe", 3500.0, Category.FOOD, now - 2 * HOUR, "Business lunch"),
            Expense(2, "Taxi to university", 1800.0, Category.TRANSPORT, now - 5 * HOUR),
            Expense(3, "Groceries", 12400.0, Category.FOOD, now - DAY, "Magnum"),
            Expense(4, "Cinema", 4000.0, Category.FUN, now - DAY - 3 * HOUR, "Two tickets"),
            Expense(5, "Internet", 7990.0, Category.BILLS, now - 2 * DAY),
            Expense(6, "Sneakers", 32000.0, Category.SHOPPING, now - 3 * DAY),
            Expense(7, "Bus card top-up", 2000.0, Category.TRANSPORT, now - 4 * DAY),
            Expense(8, "Coffee", 1200.0, Category.FOOD, now - 4 * DAY - 2 * HOUR),
            Expense(9, "Mobile plan", 3500.0, Category.BILLS, now - 6 * DAY),
            Expense(10, "Gift for a friend", 9000.0, Category.OTHER, now - 7 * DAY)
        )
    )

    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()

    private var nextId = _expenses.value.maxOf { it.id } + 1

    fun getById(id: Int): Expense? = _expenses.value.find { it.id == id }

    fun add(title: String, amount: Double, category: Category, note: String) {
        val expense = Expense(
            id = nextId++,
            title = title,
            amount = amount,
            category = category,
            date = System.currentTimeMillis(),
            note = note
        )
        _expenses.update { listOf(expense) + it }
    }

    fun delete(id: Int) {
        _expenses.update { list -> list.filterNot { it.id == id } }
    }
    fun update(expense: Expense) {
        _expenses.update { list -> list.map { if (it.id == expense.id) expense else it } }
    }
}