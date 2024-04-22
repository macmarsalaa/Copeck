package com.macmarsala.copeck.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macmarsala.copeck.database.entity.TransactionWithCategory
import com.macmarsala.copeck.domain.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel
@Inject constructor(
    transactionRepository: TransactionRepository
): ViewModel() {

    private var _uiState = MutableStateFlow(HistoryScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val transactions = transactionRepository.getAllTransactionsSortedByDate()
            _uiState.update { state -> state.copy(transactions = transactions) }
        }
    }

}

data class HistoryScreenUiState(

    val transactions: List<TransactionWithCategory> = emptyList()

)