package com.macmarsala.copeck.presentation.navigation.route

sealed class CategoryScreen(val route: String) {

    data object Income: StatsScreen("Home")

    data object Expense: StatsScreen("Stats")

}