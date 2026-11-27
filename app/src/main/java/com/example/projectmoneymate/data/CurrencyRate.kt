package com.example.projectmoneymate.data

data class CurrencyRate(
    val code: String,
    val name: String,
    val flagUrl: String,
    val kztPerUnit: Double
)