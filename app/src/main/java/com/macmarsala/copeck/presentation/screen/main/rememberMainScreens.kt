package com.macmarsala.copeck.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.macmarsala.copeck.presentation.navigation.NavigationScreen
import com.macmarsala.copeck.presentation.navigation.route.MainScreen
import com.macmarsala.copeck.ui.resources.LocalIcons
import com.macmarsala.copeck.ui.resources.LocalStrings

@Composable
fun rememberMainScreens(): List<NavigationScreen> {
    return remember {
        listOf(
            NavigationScreen(
                route = MainScreen.Home.route,
                iconId = LocalIcons.Tabs.Home,
                labelId = LocalStrings.Labels.Home
            ),
            NavigationScreen(
                route = MainScreen.Stats.route,
                iconId = LocalIcons.Tabs.Stats,
                labelId = LocalStrings.Labels.Stats
            ),
            NavigationScreen(
                route = MainScreen.History.route,
                iconId = LocalIcons.Tabs.History,
                labelId = LocalStrings.Labels.History
            )
        )
    }
}