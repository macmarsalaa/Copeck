package com.macmarsala.copeck.data.di

import com.macmarsala.copeck.data.CategoryRepositoryImpl
import com.macmarsala.copeck.data.TransactionRepositoryImpl
import com.macmarsala.copeck.domain.CategoryRepository
import com.macmarsala.copeck.domain.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

}