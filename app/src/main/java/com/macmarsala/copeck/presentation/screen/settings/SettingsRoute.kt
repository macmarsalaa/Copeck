package com.macmarsala.copeck.presentation.screen.settings

import androidx.compose.runtime.Composable

@Composable
fun SettingsRoute(
    navigateBack: () -> Unit
) {
    SettingsScreen(
        onNavigationClick = navigateBack
    )
}