package com.macmarsala.copeck.presentation.navigation.route

sealed class MainScreen(val route: String) {

    data object Home: MainScreen("Home")

    data object Stats: MainScreen("Stats")

    data object History: MainScreen("History")

}