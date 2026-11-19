package com.example.projectmoneymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.projectmoneymate.navigation.AppNavHost
import com.example.projectmoneymate.ui.theme.ProjectMoneyMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectMoneyMateTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}