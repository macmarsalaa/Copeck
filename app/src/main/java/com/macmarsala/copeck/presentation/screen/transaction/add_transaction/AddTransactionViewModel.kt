package com.macmarsala.copeck.presentation.screen.transaction.add_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macmarsala.copeck.domain.CategoryRepository
import com.macmarsala.copeck.domain.TransactionRepository
import com.macmarsala.copeck.model.Category
import com.macmarsala.copeck.model.Transaction
import com.macmarsala.copeck.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel
@Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.collect { state ->

                _uiState.value = state.copy(allFieldsFilled = state.sum.isNotEmpty() && state.category != null && state.date != 0L)

                if (state.sum.count { it == '.' } > 1) {
                    _uiState.update { it.copy(sum = state.sum.replace(".", "")) }
                }
            }
        }
    }


    fun loadCategories(transactionType: TransactionType?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
            val categoryList = when(transactionType) {
                TransactionType.INCOME -> categoryRepository.getIncomeCategories()
                TransactionType.EXPENSE -> categoryRepository.getExpenseCategories()
                else -> emptyList()
            }
            _uiState.update { state ->
                state.copy(categoryList = categoryList)
                }
            }
        }
    }

    fun onSumTextChanged(value: String) {
        val newValue = value.filter { it.isDigit() || it == '.' }
        _uiState.update { state -> state.copy(sum = newValue) }
    }

    fun editCategory(category: Category) {
        _uiState.update {
            state -> state.copy(
                category = category,
                isCategoryDialogVisible = false
            )
        }
    }

    fun editTransactionType(transactionType: TransactionType) {
        _uiState.update { state -> state.copy(transactionType = transactionType) }
    }

    fun showCategoryDialog() {
        _uiState.update { state -> state.copy(isCategoryDialogVisible = true) }
    }

    fun dismissCategoryDialog() {
        _uiState.update { state -> state.copy(isCategoryDialogVisible = false) }
    }

    fun showDatePickerDialog() {
        _uiState.update { state -> state.copy(isDatePickerDialogVisible = true) }
    }

    fun dismissDatePickerDialog() {
        _uiState.update { state -> state.copy(isDatePickerDialogVisible = false) }
    }

    fun selectDate(date: Long?) {
        _uiState.update { state ->
            state.copy(
                date = date ?: System.currentTimeMillis(),
                isDatePickerDialogVisible = false
            )
        }
    }

    fun addTransaction() {
        viewModelScope.launch {
            val state = _uiState.value
            val transaction = Transaction(
                type = state.transactionType,
                sum = state.sum.toDouble(),
                categoryId = state.category?.id ?: 0L,
                date = state.date
            )
            transactionRepository.addTransaction(transaction)
        }
    }

}

data class AddTransactionUiState(

    val isCategoryDialogVisible: Boolean = false,
    val isDatePickerDialogVisible: Boolean = false,
    val allFieldsFilled: Boolean = false,
    val categoryList: List<Category> = emptyList(),

    val transactionType: TransactionType = TransactionType.NONE,
    val sum: String = "",
    val category: Category? = null,
    val date: Long = 0L
)