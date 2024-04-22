package com.macmarsala.copeck.presentation.screen.transaction.add_transaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macmarsala.copeck.model.TransactionType

@Composable
fun AddTransactionRoute(
    navigateHome: () -> Unit,
    navigateBack: () -> Unit,
    transactionType: TransactionType,
    addTransactionViewModel: AddTransactionViewModel = hiltViewModel()
) {
    val uiState by addTransactionViewModel.uiState.collectAsStateWithLifecycle()
    addTransactionViewModel.editTransactionType(transactionType)

    AddTransactionScreen(
        uiState = uiState,
        onNavigationClick = navigateBack,
        navigateHome = navigateHome,

        onCategoryDialogClick = { addTransactionViewModel.showCategoryDialog() },
        onDatePickerDialogClick = { addTransactionViewModel.showDatePickerDialog() },
        onDismissCategoryDialog = { addTransactionViewModel.dismissCategoryDialog() },
        onDismissDatePickerDialog = { addTransactionViewModel.dismissDatePickerDialog() },

        onCategorySelect = { category -> addTransactionViewModel.editCategory(category) },
        onDateSelected = { millis -> addTransactionViewModel.selectDate(millis) },
        onTextFieldValueChange = { text -> addTransactionViewModel.onSumTextChanged(text) },

        onLoadCategories = { addTransactionViewModel.loadCategories(transactionType) },
        onAddTransaction = { addTransactionViewModel.addTransaction() }

    )
}