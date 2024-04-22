package com.macmarsala.copeck.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.macmarsala.copeck.presentation.navigation.graph.MainNavGraph
import com.macmarsala.copeck.presentation.screen.main.component.BottomNavigationBar

@Composable
fun MainRoute(
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val screens = rememberMainScreens()
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController, screens) }
    ) { innerPadding ->
        MainNavGraph(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            onIncomeClick = onIncomeClick,
            onExpenseClick = onExpenseClick,
            onCategoriesClick = onCategoriesClick,
            onSettingsClick = onSettingsClick,
            onMenuClick = onMenuClick
        )
    }
}