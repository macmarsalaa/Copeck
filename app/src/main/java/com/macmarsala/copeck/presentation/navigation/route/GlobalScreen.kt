package com.macmarsala.copeck.presentation.navigation.route

sealed class GlobalScreen(val route: String) {

    data object Main: GlobalScreen("Main")

    data object AddIncome: GlobalScreen("AddTransaction/Income")

    data object AddExpense: GlobalScreen("AddTransaction/Expense")

    data object Categories: GlobalScreen("Categories")

    data object AddCategory: GlobalScreen("AddCategory")

    data object Settings: GlobalScreen("Settings")

    data object MenuTest: GlobalScreen("MenuTest")

}