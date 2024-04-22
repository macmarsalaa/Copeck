package com.macmarsala.copeck.util

import com.macmarsala.copeck.database.entity.TransactionWithCategory
import com.macmarsala.copeck.model.CategorySummary
import com.macmarsala.copeck.model.TransactionType
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun formatLongDate(milliseconds: Long): String {
    val todayCalendar = Calendar.getInstance()
    val transactionCalendar = Calendar.getInstance()
    transactionCalendar.timeInMillis = milliseconds

    return when {
        isSameDay(todayCalendar, transactionCalendar) -> "Сегодня"
        isYesterday(transactionCalendar) -> "Вчера"
        else -> {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            dateFormat.format(transactionCalendar.time)
        }
    }
}

fun formatSum(transaction: TransactionWithCategory): String {
    val formattedSum = String.format("%.2f", transaction.transaction.sum).replace(",", ".")
    return when (transaction.transaction.type) {
        TransactionType.EXPENSE -> "- $formattedSum ₽"
        else -> "$formattedSum ₽"
    }
}

fun formatSum(categorySummary: CategorySummary): String {
    return "${String.format("%.2f", categorySummary.totalSum).replace(",", ".")} ₽"
}


private fun isSameDay(calendar1: Calendar, calendar2: Calendar): Boolean {
    return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
            calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
}

private fun isYesterday(calendar: Calendar): Boolean {
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_YEAR, -1)
    return isSameDay(yesterday, calendar)
}