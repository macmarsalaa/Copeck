package com.macmarsala.copeck.presentation.navigation.route

sealed class StatsScreen(val route: String) {

    data object Income: StatsScreen("Home")

    data object Expense: StatsScreen("Stats")

}