package com.macmarsala.copeck.presentation.screen.transaction.add_transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.macmarsala.copeck.R
import com.macmarsala.copeck.model.Category
import com.macmarsala.copeck.model.TransactionType
import com.macmarsala.copeck.util.formatLongDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    uiState: AddTransactionUiState,
    onNavigationClick: () -> Unit = {},
    onDismissCategoryDialog: () -> Unit = {},
    onDateSelected: (millis: Long?) -> Unit = {},
    onDismissDatePickerDialog: () -> Unit = {},
    onTextFieldValueChange: (text: String) -> Unit,
    onCategoryDialogClick: () -> Unit = {},
    onDatePickerDialogClick: () -> Unit = {},
    onCategorySelect: (selectedOption: Category) -> Unit = {},
    onLoadCategories: () -> Unit,
    onAddTransaction: () -> Unit,
    navigateHome: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        onLoadCategories()
    }
    
    CategoryDialog(
        uiState = uiState,
        isVisible = uiState.isCategoryDialogVisible,
        onDismiss = { onDismissCategoryDialog() },
        onCategorySelect = onCategorySelect
    )

    TransactionDatePickerDialog(
        isVisible = uiState.isDatePickerDialogVisible,
        onDateSelected = { millis -> onDateSelected(millis) },
        onDismiss = { onDismissDatePickerDialog() }
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = when (uiState.transactionType) {
                            TransactionType.INCOME ->
                                stringResource(R.string.add_transaction_screen_income).uppercase()
                            TransactionType.EXPENSE ->
                                stringResource(R.string.add_transaction_screen_expense).uppercase()
                            else -> ""
                        },
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigationClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)) {
            Spacer(Modifier.weight(1f))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.sum,
                onValueChange = { text -> onTextFieldValueChange(text)},
                label = { Text(stringResource(R.string.add_transaction_screen_sum)) },
                suffix = { Text(stringResource(id = R.string.currency_ruble)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                AssistChip(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = { onCategoryDialogClick() },
                    label = {
                        Text(
                            text = when(uiState.category) {
                                null -> stringResource(R.string.add_transaction_screen_category)
                                else -> uiState.category.name
                            }
                        )
                    }
                )
                AssistChip(
                    onClick = { onDatePickerDialogClick() },
                    label = {
                        Text(
                            text = when(uiState.date) {
                                0L -> stringResource(R.string.add_transaction_screen_date)
                                else -> formatLongDate(uiState.date)
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Row (
                horizontalArrangement = Arrangement.End
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    if (uiState.allFieldsFilled) {
                        onAddTransaction()
                        navigateHome()
                    }
                }) {
                    Text(text = stringResource(R.string.add_transaction_screen_add))
                }
            }
            Spacer(Modifier.weight(2f))
        }
    }
}