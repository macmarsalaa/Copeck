package com.macmarsala.copeck.presentation.screen.category.add_category

import androidx.compose.runtime.Composable

@Composable
fun AddCategoryRoute(
    navigateBack: () -> Unit,
    onSaveCategoryClick: () -> Unit
) {
    AddCategoryScreen(
        onNavigationClick = navigateBack,
        onSaveCategoryClick = onSaveCategoryClick
    )
}