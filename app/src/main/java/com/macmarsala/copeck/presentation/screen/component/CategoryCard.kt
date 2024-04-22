package com.macmarsala.copeck.presentation.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.macmarsala.copeck.model.Category

@Composable
fun CategoryCard(category: Category) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row (
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Avatar(iconResId = category.iconResId)
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = category.name,
                softWrap = true
            )
        }
    }
}