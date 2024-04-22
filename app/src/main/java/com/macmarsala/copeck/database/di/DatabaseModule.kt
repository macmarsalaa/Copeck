package com.macmarsala.copeck.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.macmarsala.copeck.database.CopeckDatabase
import com.macmarsala.copeck.database.dao.CategoryDao
import com.macmarsala.copeck.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CopeckDatabase {
        return Room.databaseBuilder(
            context,
            CopeckDatabase::class.java,
            CopeckDatabase.NAME
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addMigrations()
            .build()
    }

    @Provides
    fun provideTransactionDao(database: CopeckDatabase): TransactionDao {
        return database.transactionDao
    }

    @Provides
    fun provideCategoryDao(database: CopeckDatabase): CategoryDao {
        return database.categoryDao
    }

}