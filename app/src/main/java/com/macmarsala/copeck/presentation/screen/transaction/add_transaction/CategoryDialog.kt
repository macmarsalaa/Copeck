package com.macmarsala.copeck.presentation.screen.transaction.add_transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.macmarsala.copeck.R
import com.macmarsala.copeck.model.Category

@Composable
fun CategoryDialog(
    uiState: AddTransactionUiState,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onCategorySelect: (selectedOption: Category) -> Unit
) {
    var selectedOption: Category? by remember { mutableStateOf(null) }

    if (!isVisible) return

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_transaction_screen_category),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 24.dp),
                    textAlign = TextAlign.Center
                )
                LazyColumn (
                    modifier = Modifier.weight(1f)
                ) {
                    items(
                        items = uiState.categoryList,
                        key = { category -> category.id }
                    ) {
                            category ->
                        RadioButtonCategory(
                            category = category,
                            selected = selectedOption == category,
                            onSelectedChange = { selectedOption = it },
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { selectedOption?.let { onCategorySelect(it) } }
                    ) {
                        Text(text = stringResource(R.string.add_transaction_choose))
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonCategory(
    category: Category,
    selected: Boolean,
    onSelectedChange: (Category) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = { onSelectedChange(category) })
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = category.name,
            style = MaterialTheme.typography.titleMedium,
            softWrap = true
        )
    }
}