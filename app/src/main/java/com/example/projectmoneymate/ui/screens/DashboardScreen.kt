package com.example.projectmoneymate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectmoneymate.ui.components.BalanceCard
import com.example.projectmoneymate.ui.components.CategorySummary
import com.example.projectmoneymate.ui.components.EmptyState
import com.example.projectmoneymate.ui.components.ExpenseItem
import com.example.projectmoneymate.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onExpenseClick: (Int) -> Unit,
    onSeeAllClick: () -> Unit,
    viewModel: DashboardViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Good day 👋",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Here is your spending overview",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item { BalanceCard(total = state.total, count = state.count) }

        if (state.byCategory.isNotEmpty()) {
            item {
                Column {
                    SectionTitle("By category")
                    Spacer(Modifier.height(8.dp))
                    CategorySummary(items = state.byCategory)
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionTitle("Recent expenses", Modifier.weight(1f))
                TextButton(onClick = onSeeAllClick) { Text("See all") }
            }
        }

        if (state.recent.isEmpty()) {
            item { EmptyState("No expenses yet.\nTap + to add your first one") }
        } else {
            items(state.recent, key = { it.id }) { expense ->
                ExpenseItem(expense = expense, onClick = { onExpenseClick(expense.id) })
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}