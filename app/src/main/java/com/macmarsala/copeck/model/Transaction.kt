package com.macmarsala.copeck.model

data class Transaction(
    val id: Int = 0,
    val type: TransactionType,
    val sum: Double,
    val categoryId: Long,
    val date: Long
)