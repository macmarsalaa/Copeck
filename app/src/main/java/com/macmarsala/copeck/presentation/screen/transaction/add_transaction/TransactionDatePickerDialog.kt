package com.macmarsala.copeck.presentation.screen.transaction.add_transaction

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.macmarsala.copeck.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDatePickerDialog(
    isVisible: Boolean,
    onDateSelected: (millis: Long?) -> Unit,
    onDismiss: () -> Unit
) {

    if (!isVisible) return

    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = Calendar.getInstance().timeInMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
            }) {
                Text(text = stringResource(R.string.add_transaction_choose))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }

}