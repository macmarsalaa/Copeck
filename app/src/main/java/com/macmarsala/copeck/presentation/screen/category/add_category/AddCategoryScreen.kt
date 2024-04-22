package com.macmarsala.copeck.presentation.screen.category.add_category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryScreen(
    onNavigationClick: () -> Unit = {},
    onSaveCategoryClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("New category")
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigationClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onSaveCategoryClick() }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save category"
                        )
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(Modifier.padding(paddingValues)) {

        }
    }
}
