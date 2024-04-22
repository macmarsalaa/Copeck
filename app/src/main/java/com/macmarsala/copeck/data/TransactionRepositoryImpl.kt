package com.macmarsala.copeck.data

import com.macmarsala.copeck.data.mapper.toTransaction
import com.macmarsala.copeck.data.mapper.toTransactionEntity
import com.macmarsala.copeck.database.dao.TransactionDao
import com.macmarsala.copeck.database.entity.TransactionEntity
import com.macmarsala.copeck.database.entity.TransactionWithCategory
import com.macmarsala.copeck.domain.TransactionRepository
import com.macmarsala.copeck.model.CategorySummary
import com.macmarsala.copeck.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl
@Inject
constructor(
    private val transactionDao: TransactionDao
): TransactionRepository {

    override fun getCurrentBalance(): Flow<Double> {
        return transactionDao.getCurrentBalance()
    }

    override fun getCurrentIncomeSum(): Flow<Double> {
        return transactionDao.getCurrentIncomeSum()
    }

    override fun getCurrentExpenseSum(): Flow<Double> {
        return transactionDao.getCurrentExpenseSum()
    }

    override suspend fun getIncomeCategorySummariesFlow(): Flow<List<CategorySummary>> {
        return transactionDao.getIncomeCategorySummaries().map { it.toList() }
    }

    override suspend fun getExpenseCategorySummariesFlow(): Flow<List<CategorySummary>> {
        return transactionDao.getExpenseCategorySummaries().map { it.toList() }
    }

    override suspend fun getAllTransactionsSortedByDateFlow(): Flow<List<TransactionWithCategory>> {
        return transactionDao.getAllTransactionsSortedByDateFlow()
    }

    override suspend fun getAllTransactionsSortedByDate(): List<TransactionWithCategory> {
        return transactionDao.getAllTransactionsSortedByDate()
    }

    override fun getAllIncomeTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllIncomeTransactions().map {
            list -> list.map { transactionEntity -> transactionEntity.toTransaction() }
        }
    }

    override fun getAllExpenseTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllExpenseTransactions().map {
                list -> list.map { transactionEntity -> transactionEntity.toTransaction() }
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.addTransaction(transaction.toTransactionEntity())
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction.toTransactionEntity())
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction.toTransactionEntity())
    }

    override fun getTransactionsForCategoryAndDateRange(
        categoryId: Int,
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsForCategoryAndDateRange(
            categoryId, startDate, endDate)
    }
}