package com.macmarsala.copeck.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macmarsala.copeck.domain.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject constructor(
    transactionRepository: TransactionRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                transactionRepository.getCurrentBalance(),
                transactionRepository.getCurrentIncomeSum(),
                transactionRepository.getCurrentExpenseSum()
            ) {
                balance, incomeSum, expenseSum -> HomeScreenUiState(
                    balance = balance,
                    incomeSum = incomeSum,
                    expenseSum = expenseSum
                )
            }.collect { newUiState ->
                _uiState.value = newUiState
            }
        }
    }

    fun editExpanded(expanded: Boolean) {
        _uiState.update { state -> state.copy(expanded = expanded) }
    }

}

data class HomeScreenUiState (

    val balance: Double = 0.0,
    val incomeSum: Double = 0.0,
    val expenseSum: Double = 0.0,

    val expanded: Boolean = false

)