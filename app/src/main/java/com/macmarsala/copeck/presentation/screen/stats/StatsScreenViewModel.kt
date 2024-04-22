package com.macmarsala.copeck.presentation.screen.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macmarsala.copeck.domain.TransactionRepository
import com.macmarsala.copeck.model.CategorySummary
import com.macmarsala.copeck.presentation.navigation.route.StatsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsScreenViewModel
@Inject constructor(
    transactionRepository: TransactionRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(StatsScreenUiState())
    var uiState = _uiState.asStateFlow()

    fun setSelectedTab(selectedTab: String) {
        _uiState.update { state -> state.copy(selectedTab = selectedTab) }
    }

    init {
        viewModelScope.launch {
            combine(
                transactionRepository.getIncomeCategorySummariesFlow(),
                transactionRepository.getExpenseCategorySummariesFlow()
            ) { incomeCategorySummaries, expenseCategorySummaries ->
                StatsScreenUiState(
                    incomeCategorySummaries = incomeCategorySummaries,
                    expenseCategorySummaries = expenseCategorySummaries
                )
            }.stateIn(
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = StatsScreenUiState(),
                scope = viewModelScope
            ).collect { newUiState ->
                _uiState.value = newUiState
            }
        }
    }

}

data class StatsScreenUiState(

    val selectedTab: String = StatsScreen.Income.route,
    val incomeCategorySummaries: List<CategorySummary> = emptyList(),
    val expenseCategorySummaries: List<CategorySummary> = emptyList()

)