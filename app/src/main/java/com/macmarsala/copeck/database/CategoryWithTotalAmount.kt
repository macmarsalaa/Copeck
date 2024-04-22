package com.macmarsala.copeck.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.macmarsala.copeck.model.Category

data class CategoryWithTotalAmount(
    @Embedded val category: Category,
    @ColumnInfo(name = "total_amount") val totalAmount: Double
)