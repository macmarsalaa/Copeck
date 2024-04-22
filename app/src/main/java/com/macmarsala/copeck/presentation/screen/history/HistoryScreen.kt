package com.macmarsala.copeck.presentation.screen.history

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macmarsala.copeck.R
import com.macmarsala.copeck.presentation.screen.component.EmptyContentPlaceholder
import com.macmarsala.copeck.presentation.screen.component.TransactionCard
import com.macmarsala.copeck.util.formatLongDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    historyScreenViewModel: HistoryScreenViewModel = hiltViewModel()
) {

    val uiState by historyScreenViewModel.uiState.collectAsStateWithLifecycle()
    val groupedTransactions = uiState.transactions.groupBy {
        transaction -> formatLongDate(transaction.transaction.date)
    }

    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.history_screen_headline).uppercase(),
                        fontFamily = FontFamily.Monospace
                    )
                }
            )
        }
    ) {
        paddingValues ->

        EmptyContentPlaceholder(uiState.transactions)

        LazyColumn (
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            groupedTransactions.forEach { (date, transactionsForDate) ->
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                items(transactionsForDate) { transaction ->
                    TransactionCard(transactionWithCategory = transaction)
                }
            }
        }
    }
}