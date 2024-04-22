package com.macmarsala.copeck.presentation.screen.category.categories

import androidx.compose.runtime.Composable

@Composable
fun CategoriesRoute(
    navigateBack: () -> Unit,
    onAddCategoryClick: () -> Unit
) {
    CategoriesScreen(
        onNavigationClick = navigateBack,
        onAddCategoryClick = onAddCategoryClick
    )
}