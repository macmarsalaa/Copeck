package com.macmarsala.copeck.presentation.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.macmarsala.copeck.database.entity.TransactionWithCategory
import com.macmarsala.copeck.util.formatSum

@Composable
fun TransactionCard(transactionWithCategory: TransactionWithCategory) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Avatar(iconResId = transactionWithCategory.category.iconResId)
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = transactionWithCategory.category.name
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = formatSum(transactionWithCategory),
                fontWeight = FontWeight.Bold
            )
        }
    }
    Spacer(modifier = Modifier.size(16.dp))
}