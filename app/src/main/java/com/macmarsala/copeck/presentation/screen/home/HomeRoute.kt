package com.macmarsala.copeck.presentation.screen.home

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    HomeScreen(
        onIncomeClick = onIncomeClick,
        onExpenseClick = onExpenseClick,
        onCategoriesClick = onCategoriesClick,
        onSettingsClick = onSettingsClick,
        onMenuClick = onMenuClick
    )
}