package com.macmarsala.copeck.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macmarsala.copeck.presentation.navigation.route.MainScreen
import com.macmarsala.copeck.presentation.screen.history.HistoryScreen
import com.macmarsala.copeck.presentation.screen.home.HomeRoute
import com.macmarsala.copeck.presentation.screen.stats.StatsScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onMenuClick: () -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainScreen.Home.route
    ) {
        composable(MainScreen.Home.route) {
            HomeRoute(
                onIncomeClick = onIncomeClick,
                onExpenseClick = onExpenseClick,
                onCategoriesClick = onCategoriesClick,
                onSettingsClick = onSettingsClick,
                onMenuClick = onMenuClick
            )
        }
        composable(MainScreen.Stats.route) {
            StatsScreen()
        }
        composable(MainScreen.History.route) {
            HistoryScreen()
        }
    }
}