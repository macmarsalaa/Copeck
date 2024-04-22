package com.macmarsala.copeck.model

data class Category(
    val id: Long,
    val type: TransactionType,
    val name: String,
    val iconResId: Int
)
