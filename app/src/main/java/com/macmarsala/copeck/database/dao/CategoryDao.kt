package com.macmarsala.copeck.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.macmarsala.copeck.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY type")
    fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories ORDER BY type")
    fun getCategoriesFlow(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE type = 'INCOME'")
    fun getIncomeCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE type = 'INCOME'")
    fun getIncomeCategoriesFlow(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE type = 'EXPENSE'")
    fun getExpenseCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE type = 'EXPENSE'")
    fun getExpenseCategoriesFlow(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllCategories(vararg categories: CategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)
}