package com.example.projectmoneymate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectmoneymate.data.Category

@Composable
fun CategoryChipRow(
    selected: Category?,
    onSelect: (Category?) -> Unit,
    showAll: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (showAll) {
            item {
                CategoryChip(text = "All", selected = selected == null, onClick = { onSelect(null) })
            }
        }
        items(Category.entries) { category ->
            CategoryChip(
                text = "${category.emoji} ${category.title}",
                selected = selected == category,
                onClick = { onSelect(category) }
            )
        }
    }
}