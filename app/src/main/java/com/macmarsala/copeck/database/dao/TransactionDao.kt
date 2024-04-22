package com.macmarsala.copeck.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.macmarsala.copeck.database.entity.TransactionEntity
import com.macmarsala.copeck.database.entity.TransactionWithCategory
import com.macmarsala.copeck.model.CategorySummary
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT " +
            "((SELECT IFNULL(SUM(sum), 0) FROM transactions WHERE type = 'INCOME') - " +
            "(SELECT IFNULL(SUM(sum), 0) FROM transactions WHERE type = 'EXPENSE')) " +
            "AS balance FROM transactions LIMIT 1")
    fun getCurrentBalance(): Flow<Double>

    @Query("SELECT SUM(sum) FROM transactions WHERE type = 'INCOME'")
    fun getCurrentIncomeSum(): Flow<Double>

    @Query("SELECT SUM(sum) FROM transactions WHERE type = 'EXPENSE'")
    fun getCurrentExpenseSum(): Flow<Double>

    @Query("SELECT c.name " +
            "AS categoryName, c.iconResId, SUM(t.sum) " +
            "AS totalSum FROM categories c " +
            "INNER JOIN transactions t " +
            "ON t.categoryId = c.id " +
            "WHERE t.type = 'INCOME' " +
            "GROUP BY c.name")
    fun getIncomeCategorySummaries(): Flow<List<CategorySummary>>

    @Query("SELECT c.name " +
            "AS categoryName, c.iconResId, SUM(t.sum) " +
            "AS totalSum FROM categories c " +
            "INNER JOIN transactions t " +
            "ON t.categoryId = c.id " +
            "WHERE t.type = 'EXPENSE' " +
            "GROUP BY c.name")
    fun getExpenseCategorySummaries(): Flow<List<CategorySummary>>

    @Transaction
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactionsSortedByDateFlow(): Flow<List<TransactionWithCategory>>

    @Transaction
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactionsSortedByDate(): List<TransactionWithCategory>

    @Query("SELECT * FROM transactions WHERE type = 'INCOME'")
    fun getAllIncomeTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE type = 'EXPENSE'")
    fun getAllExpenseTransactions(): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(transaction: TransactionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("SELECT * " +
            "FROM transactions " +
            "WHERE categoryId = :categoryId " +
            "AND date BETWEEN :startDate AND :endDate")
    fun getTransactionsForCategoryAndDateRange(
        categoryId: Int,
        startDate: Long,
        endDate: Long): Flow<List<TransactionEntity>>

}