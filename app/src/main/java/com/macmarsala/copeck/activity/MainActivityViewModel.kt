package com.macmarsala.copeck.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macmarsala.copeck.domain.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    fun prepareData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (categoryRepository.getCategories().isEmpty())
                    categoryRepository.insertDefaultCategories()
            }
        }
    }
}