package com.example.projectmoneymate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectmoneymate.ui.components.AppTopBar
import com.example.projectmoneymate.ui.components.EmptyState
import com.example.projectmoneymate.ui.components.ErrorState
import com.example.projectmoneymate.ui.components.LoadingState
import com.example.projectmoneymate.ui.components.RateItem
import com.example.projectmoneymate.viewmodel.RatesUiState
import com.example.projectmoneymate.viewmodel.RatesViewModel

@Composable
fun RatesScreen(viewModel: RatesViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = "Exchange rates",
            actions = {
                IconButton(onClick = viewModel::loadRates) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                }
            }
        )

        OutlinedTextField(
            value = query,
            onValueChange = viewModel::onQueryChange,
            label = { Text("Search currency") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )


        when (val s = state) {
            is RatesUiState.Loading -> LoadingState("Loading exchange rates...")
            is RatesUiState.Error -> ErrorState(message = s.message, onRetry = viewModel::loadRates)
            is RatesUiState.Empty -> EmptyState("No currencies found")
            is RatesUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            text = "1 unit in tenge • Updated: ${s.updated}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    items(s.rates, key = { it.code }) { rate ->
                        RateItem(rate = rate)
                    }
                }
            }
        }
    }
}