package com.macmarsala.copeck.data

import com.macmarsala.copeck.data.mapper.toCategory
import com.macmarsala.copeck.data.mapper.toCategoryEntity
import com.macmarsala.copeck.database.dao.CategoryDao
import com.macmarsala.copeck.domain.CategoryRepository
import com.macmarsala.copeck.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl
@Inject
constructor(
    private val categoryDao: CategoryDao
): CategoryRepository {

    override suspend fun insertDefaultCategories() {
        defaultCategories.forEach { categoryDao.addCategory(it.toCategoryEntity()) }
    }

    override suspend fun getCategories(): List<Category> {
        return categoryDao.getCategories().map { it.toCategory() }
    }

    override var categories: Flow<List<Category>> = categoryDao.getCategoriesFlow()
        .map { list -> list.map { categoryEntity -> categoryEntity.toCategory() } }

    override var incomeCategories: Flow<List<Category>> = categoryDao.getIncomeCategoriesFlow()
        .map { list -> list.map { categoryEntity -> categoryEntity.toCategory() } }

    override var expenseCategories: Flow<List<Category>> = categoryDao.getExpenseCategoriesFlow()
        .map { list -> list.map { categoryEntity -> categoryEntity.toCategory() } }

    override suspend fun getIncomeCategories(): List<Category> {
        return categoryDao.getIncomeCategories().map { it.toCategory() }
    }

    override fun getIncomeCategoriesFlow(): Flow<List<Category>> {
        return categoryDao.getIncomeCategoriesFlow().map {
                list -> list.map { categoryEntity -> categoryEntity.toCategory() }
        }
    }

    override suspend fun getExpenseCategories(): List<Category> {
        return categoryDao.getExpenseCategories().map { it.toCategory() }
    }

    override fun getExpenseCategoriesFlow(): Flow<List<Category>> {
        return categoryDao.getExpenseCategoriesFlow().map {
                list -> list.map { categoryEntity -> categoryEntity.toCategory() }
        }
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.addCategory(category.toCategoryEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.toCategoryEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.toCategoryEntity())
    }

}