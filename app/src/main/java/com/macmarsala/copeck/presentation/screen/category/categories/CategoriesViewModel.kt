package com.macmarsala.copeck.presentation.screen.category.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macmarsala.copeck.domain.CategoryRepository
import com.macmarsala.copeck.model.Category
import com.macmarsala.copeck.presentation.navigation.route.CategoryScreen
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
class CategoriesViewModel
@Inject
constructor(
    categoryRepository: CategoryRepository
): ViewModel() {

    private var _uiState = MutableStateFlow(CategoriesUiState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                categoryRepository.incomeCategories,
                categoryRepository.expenseCategories
            ) { incomeCategories, expenseCategories ->
                CategoriesUiState(
                    incomeCategories = incomeCategories,
                    expenseCategories = expenseCategories
                )
            }.stateIn(
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CategoriesUiState(),
                scope = viewModelScope
            ).collect { newUiState ->
                _uiState.value = newUiState
            }
        }
    }

    fun setSelectedTab(selectedTab: String) {
        _uiState.update { state -> state.copy(selectedTab = selectedTab) }
    }

}

data class CategoriesUiState(

    val incomeCategories: List<Category> = listOf(),
    val expenseCategories: List<Category> = listOf(),
    val selectedTab: String = CategoryScreen.Income.route

)