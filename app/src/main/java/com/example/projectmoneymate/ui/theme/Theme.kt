package com.example.projectmoneymate.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
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

private val DarkColors = darkColorScheme(
    primary = PurpleGradientEnd,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF3A2C7A),
    onPrimaryContainer = Color.White,
    secondaryContainer = Color(0xFF3A2C7A),
    onSecondaryContainer = Color.White,
    background = Color(0xFF121218),
    onBackground = Color(0xFFECEAF4),
    surface = Color(0xFF1E1D27),
    onSurface = Color(0xFFECEAF4),
    onSurfaceVariant = Color(0xFFA9A6BC),
    error = Color(0xFFFF6B6F)
)

@Composable
fun ProjectMoneyMateTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}