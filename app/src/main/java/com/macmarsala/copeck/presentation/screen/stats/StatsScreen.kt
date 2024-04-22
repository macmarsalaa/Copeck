package com.macmarsala.copeck.presentation.screen.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.macmarsala.copeck.R
import com.macmarsala.copeck.presentation.navigation.route.StatsScreen
import com.macmarsala.copeck.presentation.screen.component.CategorySummaryCard
import com.macmarsala.copeck.presentation.screen.component.EmptyContentPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    statsScreenViewModel: StatsScreenViewModel = hiltViewModel()
) {

    val uiState by statsScreenViewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.stats_screen_headline).uppercase(),
                        fontFamily = FontFamily.Monospace
                    )
                }
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
                    onClick = { statsScreenViewModel.setSelectedTab(StatsScreen.Income.route) },
                    shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
                    icon = { SegmentedButtonDefaults.Icon(active = false) }
                ) {
                    Text(text = stringResource(R.string.stats_screen_incomes))
                }
                SegmentedButton(
                    selected = uiState.selectedTab == StatsScreen.Expense.route,
                    onClick = { statsScreenViewModel.setSelectedTab(StatsScreen.Expense.route) },
                    shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
                    icon = { SegmentedButtonDefaults.Icon(active = false) }
                ) {
                    Text(text = stringResource(R.string.stats_screen_expenses))
                }
            }

            when(uiState.selectedTab) {

                StatsScreen.Income.route -> {

                    EmptyContentPlaceholder(uiState.incomeCategorySummaries)

                    LazyColumn {
                        items(uiState.incomeCategorySummaries) {
                            summary -> CategorySummaryCard(summary)
                        }
                    }
                }

                StatsScreen.Expense.route -> {

                    EmptyContentPlaceholder(uiState.expenseCategorySummaries)

                    LazyColumn {
                        items(uiState.expenseCategorySummaries) {
                            summary -> CategorySummaryCard(summary)
                        }
                    }
                }

            }
        }
    }
}

