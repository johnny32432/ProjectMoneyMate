package com.example.projectmoneymate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectmoneymate.data.Category
import com.example.projectmoneymate.data.Expense

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long,
    val note: String
)

fun ExpenseEntity.toExpense(): Expense = Expense(
    id = id,
    title = title,
    amount = amount,
    category = Category.entries.find { it.name == category } ?: Category.OTHER,
    date = date,
    note = note
)

fun Expense.toEntity(): ExpenseEntity = ExpenseEntity(
    id = id,
    title = title,
    amount = amount,
    category = category.name,
    date = date,
    note = note
)