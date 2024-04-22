package com.macmarsala.copeck.presentation.screen.menu_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MenuTestViewModel: ViewModel() {

    private var _uiState = MutableStateFlow(MenuTestUiState())
    val uiState = _uiState.asStateFlow()

    fun showSnackbar() {
        _uiState.update { state -> state.copy(showSnackbar = true) }
    }

    fun showToast() {
        _uiState.update { state -> state.copy(showToast = true) }
    }

    fun hideSnackbar() {
        _uiState.update { state -> state.copy(showSnackbar = false) }
    }

    fun hideToast() {
        _uiState.update { state -> state.copy(showToast = false) }
    }

}

data class MenuTestUiState(

    val showSnackbar: Boolean = false,
    val showToast: Boolean = false

)