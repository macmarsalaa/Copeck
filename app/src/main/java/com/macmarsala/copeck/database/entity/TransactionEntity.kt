package com.macmarsala.copeck.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.macmarsala.copeck.model.TransactionType

@Entity(tableName = TransactionEntity.TABLE_NAME, foreignKeys = [
    ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.NO_ACTION
    )
],
    indices = [Index(value = ["categoryId"])]
)

data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: TransactionType,
    val sum: Double,
    val categoryId: Long,
    val date: Long
) {
    companion object {
        const val TABLE_NAME = "transactions"
    }
}