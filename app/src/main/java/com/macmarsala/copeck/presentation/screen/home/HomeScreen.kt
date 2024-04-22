package com.macmarsala.copeck.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macmarsala.copeck.R
import com.macmarsala.copeck.ui.resources.LocalIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    onIncomeClick: () -> Unit = {},
    onExpenseClick: () -> Unit = {},
    onCategoriesClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {

    val uiState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.home_screen_headline).uppercase(),
                        fontFamily = FontFamily.Monospace
                    )
                },
                actions = {
                    IconButton(onClick = { homeScreenViewModel.editExpanded(true) }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = uiState.expanded,
                        onDismissRequest = { homeScreenViewModel.editExpanded(false) }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                onMenuClick()
                                homeScreenViewModel.editExpanded(false)
                            },
                            text = { Text(text = stringResource(R.string.menu_test_headline)) }
                        )

                    }
                }
            )
        }
    ) {
        paddingValues ->
        LazyColumn (
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {

            item {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(id = R.string.home_screen_balance),
                    modifier = Modifier.alpha(0.5f),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            item {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "${String.format("%.2f", uiState.balance).replace(",", ".")} ₽",
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                ) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            onClick = { onIncomeClick() }
                        ) {
                            Icon(
                                painter = painterResource(id = LocalIcons.Home.Income),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(40.dp)
                            )
                        }
                    }
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            onClick = { onExpenseClick() }
                        ) {
                            Icon(
                                painter = painterResource(id = LocalIcons.Home.Expense),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(40.dp)
                            )
                        }
                    }
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            onClick = { onCategoriesClick() }
                        ) {
                            Icon(
                                painter = painterResource(id = LocalIcons.Home.Categories),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(40.dp)
                            )
                        }
                    }
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            onClick = { onSettingsClick() }
                        ) {
                            Icon(
                                painter = painterResource(id = LocalIcons.Home.Settings),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(40.dp)
                            )
                        }
                    }
                }
            }

            item {
                Card {
                    Row(
                        Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp, end = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.home_screen_incomes),
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row {
                        Text(
                            text = "${String.format("%.2f", uiState.incomeSum).replace(",", ".")} ₽",
                            softWrap = true,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, bottom = 24.dp, end = 16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.size(24.dp))

                Card {
                    Row(
                        Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp, end = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.home_screen_expenses),
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row {
                        Text(
                            text = "${String.format("%.2f", uiState.expenseSum).replace(",", ".")} ₽",
                            softWrap = true,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, bottom = 24.dp, end = 16.dp)
                        )
                    }
                }
            }
        }
    }
}