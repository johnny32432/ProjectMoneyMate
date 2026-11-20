package com.example.projectmoneymate.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar

fun formatAmount(amount: Double): String {
    val symbols = DecimalFormatSymbols(Locale.US).apply { groupingSeparator = ' ' }
    return DecimalFormat("#,##0.##", symbols).format(amount) + " ₸"
}

fun formatDate(millis: Long): String =
    SimpleDateFormat("d MMM yyyy, HH:mm", Locale.ENGLISH).format(Date(millis))

fun formatDayHeader(millis: Long): String {
    val day = Calendar.getInstance().apply { timeInMillis = millis }
    val today = Calendar.getInstance()
    val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }

    fun isSameDay(a: Calendar, b: Calendar) =
        a.get(Calendar.YEAR) == b.get(Calendar.YEAR) &&
                a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR)

    return when {
        isSameDay(day, today) -> "Today"
        isSameDay(day, yesterday) -> "Yesterday"
        else -> SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).format(Date(millis))
    }
}