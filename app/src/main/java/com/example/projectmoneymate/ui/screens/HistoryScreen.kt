package com.example.projectmoneymate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectmoneymate.ui.components.AppTopBar
import com.example.projectmoneymate.ui.components.CategoryChipRow
import com.example.projectmoneymate.ui.components.EmptyState
import com.example.projectmoneymate.ui.components.ExpenseItem
import com.example.projectmoneymate.util.formatAmount
import com.example.projectmoneymate.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(
    onExpenseClick: (Int) -> Unit,
    viewModel: HistoryViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(title = "History")

        CategoryChipRow(
            selected = state.selectedCategory,
            onSelect = viewModel::selectCategory,
            showAll = true,
            contentPadding = PaddingValues(horizontal = 16.dp)
        )

        Text(
            text = "Total: ${formatAmount(state.total)}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        if (state.groups.isEmpty()) {
            EmptyState("No expenses in this category yet")
        } else {
            LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.groups.forEach { group ->
                    item(key = "header_${group.header}") {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = group.header,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = formatAmount(group.total),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    items(group.expenses, key = { it.id }) { expense ->
                        ExpenseItem(expense = expense, onClick = { onExpenseClick(expense.id) })
                    }
                }
            }
        }
    }
}