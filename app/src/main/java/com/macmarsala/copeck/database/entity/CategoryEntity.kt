package com.macmarsala.copeck.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.macmarsala.copeck.model.TransactionType

@Entity(tableName = CategoryEntity.TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: TransactionType,
    val name: String,
    val iconResId: Int
) {
    companion object {
        const val TABLE_NAME = "categories"
    }
}