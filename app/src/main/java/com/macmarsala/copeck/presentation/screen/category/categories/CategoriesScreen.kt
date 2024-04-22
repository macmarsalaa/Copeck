package com.macmarsala.copeck.presentation.screen.category.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macmarsala.copeck.presentation.navigation.route.StatsScreen
import com.macmarsala.copeck.presentation.screen.component.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    onNavigationClick: () -> Unit = {},
    onAddCategoryClick: () -> Unit = {}
) {

    val uiState by categoriesViewModel.uiState.collectAsStateWithLifecycle()
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(appBarState)

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Категории")
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigationClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
            ) {
                SegmentedButton(
                    selected = uiState.selectedTab == StatsScreen.Income.route,
                    onClick = { categoriesViewModel.setSelectedTab(StatsScreen.Income.route) },
                    shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
                    icon = { SegmentedButtonDefaults.Icon(active = false) }
                ) {
                    Text(text = "Доходы")
                }
                SegmentedButton(
                    selected = uiState.selectedTab == StatsScreen.Expense.route,
                    onClick = { categoriesViewModel.setSelectedTab(StatsScreen.Expense.route) },
                    shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
                    icon = { SegmentedButtonDefaults.Icon(active = false) }
                ) {
                    Text(text = "Расходы")
                }
            }

            when(uiState.selectedTab) {

                StatsScreen.Income.route -> {
                    LazyColumn(
                        Modifier.padding(top = 8.dp)
                    ) {
                        items(
                            items = uiState.incomeCategories,
                            key = { category -> category.id }
                        ) {
                                category ->
                            CategoryCard(
                                category = category
                            )
                        }
                    }
                }

                StatsScreen.Expense.route -> {
                    LazyColumn(
                        Modifier.padding(top = 8.dp)
                    ) {
                        items(
                            items = uiState.expenseCategories,
                            key = { category -> category.id }
                        ) {
                                category ->
                            CategoryCard(
                                category = category
                            )
                        }
                    }
                }

            }
        }
    }
}