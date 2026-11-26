package com.example.projectmoneymate.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        TabItem(navController, currentRoute, Screen.Dashboard, Icons.Filled.Home, "Home")
        TabItem(navController, currentRoute, Screen.History, Icons.Filled.DateRange, "History")

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

        TabItem(navController, currentRoute, Screen.Rates, Icons.Filled.Info, "Rates")
        TabItem(navController, currentRoute, Screen.Settings, Icons.Filled.Settings, "Settings")
    }
}

@Composable
private fun RowScope.TabItem(
    navController: NavHostController,
    currentRoute: String?,
    screen: Screen,
    icon: ImageVector,
    label: String
) {
    NavigationBarItem(
        selected = currentRoute == screen.route,
        onClick = { navController.navigateToTab(screen.route) },
        icon = { Icon(icon, contentDescription = null) },
        label = { Text(label) }
    )
}