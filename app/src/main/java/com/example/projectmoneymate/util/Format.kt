package com.example.projectmoneymate.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatAmount(amount: Double): String {
    val symbols = DecimalFormatSymbols(Locale.US).apply { groupingSeparator = ' ' }
    return DecimalFormat("#,##0.##", symbols).format(amount) + " ₸"
}

fun formatDate(millis: Long): String =
    SimpleDateFormat("d MMM yyyy, HH:mm", Locale.ENGLISH).format(Date(millis))