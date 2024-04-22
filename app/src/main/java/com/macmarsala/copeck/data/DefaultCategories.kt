package com.macmarsala.copeck.data

import com.macmarsala.copeck.model.Category
import com.macmarsala.copeck.model.TransactionType
import com.macmarsala.copeck.ui.resources.LocalIcons
import kotlin.random.Random

var defaultCategories = mutableListOf(

    // EXPENSE

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "Еда",
        iconResId = LocalIcons.Expense.Food
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "Продукты",
        iconResId = LocalIcons.Expense.Grocery
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "Транспорт",
        iconResId = LocalIcons.Expense.Transport
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "ЖКХ",
        iconResId = LocalIcons.Expense.Apartment
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "Здоровье",
        iconResId = LocalIcons.Expense.Health
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "Развлечения",
        iconResId = LocalIcons.Expense.Joy
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.EXPENSE,
        name = "Связь",
        iconResId = LocalIcons.Expense.Internet
    ),

    // INCOME

    Category(
        id = Random.nextLong(),
        type = TransactionType.INCOME,
        name = "Зарплата",
        iconResId = LocalIcons.Income.Salary
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.INCOME,
        name = "Подработка",
        iconResId = LocalIcons.Income.Freelance
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.INCOME,
        name = "Стипендия",
        iconResId = LocalIcons.Income.Grants
    ),

    Category(
        id = Random.nextLong(),
        type = TransactionType.INCOME,
        name = "Подарки",
        iconResId = LocalIcons.Income.Gifts
    )

)