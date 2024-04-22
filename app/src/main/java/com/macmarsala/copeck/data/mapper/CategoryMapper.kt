package com.macmarsala.copeck.data.mapper

import com.macmarsala.copeck.database.entity.CategoryEntity
import com.macmarsala.copeck.model.Category

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        type = type,
        name = name,
        iconResId = iconResId
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        type = type,
        name = name,
        iconResId = iconResId
    )
}