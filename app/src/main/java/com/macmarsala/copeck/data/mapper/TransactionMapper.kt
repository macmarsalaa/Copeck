package com.macmarsala.copeck.data.mapper

import com.macmarsala.copeck.database.entity.TransactionEntity
import com.macmarsala.copeck.model.Transaction

fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        id = id,
        type = type,
        sum = sum,
        categoryId = categoryId,
        date = date
    )
}

fun Transaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        type = type,
        sum = sum,
        categoryId = categoryId,
        date = date
    )
}