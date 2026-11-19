package com.example.projectmoneymate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.projectmoneymate.ui.screens.AddExpenseScreen
import com.example.projectmoneymate.ui.screens.DashboardScreen
import com.example.projectmoneymate.ui.screens.DetailsScreen
import com.example.projectmoneymate.ui.screens.HistoryScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onExpenseClick = { id -> navController.navigate(Screen.Details.createRoute(id)) },
                onSeeAllClick = { navController.navigateToTab(Screen.History.route) }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onExpenseClick = { id -> navController.navigate(Screen.Details.createRoute(id)) }
            )
        }

        composable(Screen.AddExpense.route) {
            AddExpenseScreen(
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(Screen.Details.ARG_ID) { type = NavType.IntType })
        ) {
            DetailsScreen(onBack = { navController.popBackStack() })
        }
    }
}

fun NavHostController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}