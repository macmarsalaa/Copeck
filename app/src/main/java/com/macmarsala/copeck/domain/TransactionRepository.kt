package com.macmarsala.copeck.domain

import com.macmarsala.copeck.database.entity.TransactionEntity
import com.macmarsala.copeck.database.entity.TransactionWithCategory
import com.macmarsala.copeck.model.CategorySummary
import com.macmarsala.copeck.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getCurrentBalance(): Flow<Double>

    fun getCurrentIncomeSum(): Flow<Double>

    fun getCurrentExpenseSum(): Flow<Double>

    suspend fun getIncomeCategorySummariesFlow(): Flow<List<CategorySummary>>

    suspend fun getExpenseCategorySummariesFlow(): Flow<List<CategorySummary>>

    suspend fun getAllTransactionsSortedByDate(): List<TransactionWithCategory>

    suspend fun getAllTransactionsSortedByDateFlow(): Flow<List<TransactionWithCategory>>

    fun getAllIncomeTransactions(): Flow<List<Transaction>>

    fun getAllExpenseTransactions(): Flow<List<Transaction>>

    suspend fun addTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    fun getTransactionsForCategoryAndDateRange(
        categoryId: Int,
        startDate: Long,
        endDate: Long): Flow<List<TransactionEntity>>

}