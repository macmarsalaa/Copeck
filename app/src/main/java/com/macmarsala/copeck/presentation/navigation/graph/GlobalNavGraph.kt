package com.macmarsala.copeck.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macmarsala.copeck.model.TransactionType
import com.macmarsala.copeck.presentation.navigation.route.GlobalScreen
import com.macmarsala.copeck.presentation.screen.category.add_category.AddCategoryRoute
import com.macmarsala.copeck.presentation.screen.category.categories.CategoriesRoute
import com.macmarsala.copeck.presentation.screen.main.MainRoute
import com.macmarsala.copeck.presentation.screen.menu_test.MenuTestRoute
import com.macmarsala.copeck.presentation.screen.settings.SettingsRoute
import com.macmarsala.copeck.presentation.screen.transaction.add_transaction.AddTransactionRoute

@Composable
fun GlobalNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost (
        modifier = modifier,
        navController = navController,
        startDestination = GlobalScreen.Main.route
    ) {
        composable(
            route = GlobalScreen.Main.route
        ) {
            MainRoute(
                onIncomeClick = { navController.navigate(GlobalScreen.AddIncome.route) },
                onExpenseClick = { navController.navigate(GlobalScreen.AddExpense.route) },
                onCategoriesClick = { navController.navigate(GlobalScreen.Categories.route) },
                onSettingsClick = { navController.navigate(GlobalScreen.Settings.route) },
                onMenuClick = { navController.navigate(GlobalScreen.MenuTest.route) }
            )
        }

        composable(
            route = GlobalScreen.MenuTest.route
        ) {
            MenuTestRoute(
                onNavigationClick = { navController.popBackStack() }
            )
        }

        composable(
            route = GlobalScreen.AddIncome.route
        ) {
            AddTransactionRoute(
                navigateHome = { navController.navigate(GlobalScreen.Main.route) },
                navigateBack = { navController.popBackStack() },
                transactionType = TransactionType.INCOME
            )
        }

        composable(
            route = GlobalScreen.AddExpense.route
        ) {
            AddTransactionRoute(
                navigateHome = { navController.navigate(GlobalScreen.Main.route) },
                navigateBack = { navController.popBackStack() },
                transactionType = TransactionType.EXPENSE
            )
        }

        composable(
            route = GlobalScreen.Categories.route
        ) {
            CategoriesRoute(
                navigateBack = { navController.popBackStack() },
                onAddCategoryClick = { navController.navigate(GlobalScreen.AddCategory.route) }
            )
        }

        composable(
            route = GlobalScreen.AddCategory.route
        ) {
            AddCategoryRoute(
                navigateBack = { navController.popBackStack() },
                onSaveCategoryClick = {  }
            )
        }

        composable(
            route = GlobalScreen.Settings.route
        ) {
            SettingsRoute(
                navigateBack = { navController.popBackStack() }
            )
        }
    }

}