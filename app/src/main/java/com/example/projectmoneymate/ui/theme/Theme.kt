package com.example.projectmoneymate.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Purple,
    onPrimary = Color.White,
    primaryContainer = PurpleLight,
    onPrimaryContainer = Purple,
    secondaryContainer = PurpleLight,
    onSecondaryContainer = Purple,
    background = AppBackground,
    onBackground = TextDark,
    surface = Color.White,
    onSurface = TextDark,
    onSurfaceVariant = TextGray,
    error = ExpenseRed
)


@Composable
fun ProjectMoneyMateTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}