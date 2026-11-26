package com.example.projectmoneymate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectmoneymate.data.Expense
import com.example.projectmoneymate.ui.components.AppTopBar
import com.example.projectmoneymate.ui.components.CategoryIcon
import com.example.projectmoneymate.ui.components.DeleteExpenseDialog
import com.example.projectmoneymate.ui.components.DetailRow
import com.example.projectmoneymate.ui.components.EmptyState
import com.example.projectmoneymate.util.formatAmount
import com.example.projectmoneymate.util.formatDate
import com.example.projectmoneymate.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(
    onBack: () -> Unit,
    onEdit: (Int) -> Unit = {},
    viewModel: DetailsViewModel = viewModel()
) {
    val expense by viewModel.expense.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = "Details",
            onBack = onBack,
            actions = {
                if (expense != null) {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        )

        val current = expense
        if (current == null) {
            EmptyState("Expense not found")
        } else {
            ExpenseDetailsContent(current)
        }
    }

    if (showDeleteDialog) {
        DeleteExpenseDialog(
            onConfirm = {
                showDeleteDialog = false
                viewModel.delete()
                onBack()
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}

@Composable
private fun ExpenseDetailsContent(expense: Expense) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryIcon(category = expense.category, size = 72.dp)
        Spacer(Modifier.height(12.dp))
        Text(
            text = expense.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "-" + formatAmount(expense.amount),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                DetailRow("Category", "${expense.category.emoji} ${expense.category.title}")
                HorizontalDivider()
                DetailRow("Date", formatDate(expense.date))
                HorizontalDivider()
                DetailRow("Note", expense.note.ifBlank { "—" })
            }
        }
    }
}