package com.example.projectmoneymate.data

import android.content.Context
import com.example.projectmoneymate.data.local.AppDatabase
import com.example.projectmoneymate.data.local.ExpenseDao
import com.example.projectmoneymate.data.local.ExpenseEntity
import com.example.projectmoneymate.data.local.SampleData
import com.example.projectmoneymate.data.local.toEntity
import com.example.projectmoneymate.data.local.toExpense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

object ExpenseRepository {

    private lateinit var dao: ExpenseDao

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun init(context: Context) {
        if (::dao.isInitialized) return
        dao = AppDatabase.getInstance(context).expenseDao()
        scope.launch {
            if (dao.count() == 0) dao.insertAll(SampleData.expenses())
        }
    }

    val expenses: StateFlow<List<Expense>> by lazy {
        dao.getAll()
            .map { list -> list.map { it.toExpense() } }
            .stateIn(scope, SharingStarted.Eagerly, emptyList())
    }

    fun getById(id: Int): Expense? = expenses.value.find { it.id == id }

    fun add(title: String, amount: Double, category: Category, note: String) {
        scope.launch {
            dao.insert(
                ExpenseEntity(
                    title = title,
                    amount = amount,
                    category = category.name,
                    date = System.currentTimeMillis(),
                    note = note
                )
            )
        }
    }

    fun update(expense: Expense) {
        scope.launch { dao.update(expense.toEntity()) }
    }


    fun delete(id: Int) {
        scope.launch { dao.deleteById(id) }
    }
}