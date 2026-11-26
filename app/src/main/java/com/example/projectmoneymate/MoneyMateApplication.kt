package com.example.projectmoneymate

import android.app.Application
import com.example.projectmoneymate.data.ExpenseRepository

class MoneyMateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ExpenseRepository.init(this)
    }
}