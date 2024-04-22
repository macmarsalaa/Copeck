package com.macmarsala.copeck.domain

import com.macmarsala.copeck.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun insertDefaultCategories()

    suspend fun getCategories(): List<Category>

    val categories: Flow<List<Category>>

    val incomeCategories: Flow<List<Category>>

    val expenseCategories: Flow<List<Category>>

    suspend fun getIncomeCategories(): List<Category>

    suspend fun getExpenseCategories(): List<Category>

    fun getIncomeCategoriesFlow(): Flow<List<Category>>

    fun getExpenseCategoriesFlow(): Flow<List<Category>>

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

}