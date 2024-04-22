package com.macmarsala.copeck.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.macmarsala.copeck.database.dao.CategoryDao
import com.macmarsala.copeck.database.dao.TransactionDao
import com.macmarsala.copeck.database.entity.CategoryEntity
import com.macmarsala.copeck.database.entity.TransactionEntity

@Database(
    version = 1,
    entities = [
        TransactionEntity::class,
        CategoryEntity::class
    ]
)

abstract class CopeckDatabase: RoomDatabase() {

    abstract val transactionDao: TransactionDao
    abstract val categoryDao: CategoryDao

    companion object {
        const val NAME = "copeck-database"
    }
}