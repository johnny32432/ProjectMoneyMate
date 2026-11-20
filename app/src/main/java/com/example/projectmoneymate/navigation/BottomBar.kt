package com.example.projectmoneymate.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        NavigationBarItem(
            selected = currentRoute == Screen.Dashboard.route,
            onClick = { navController.navigateToTab(Screen.Dashboard.route) },
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Dashboard") }
        )


        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddExpense.route) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add expense")
            }
        }

        NavigationBarItem(
            selected = currentRoute == Screen.History.route,
            onClick = { navController.navigateToTab(Screen.History.route) },
            icon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
            label = { Text("History") }
        )
    }
}