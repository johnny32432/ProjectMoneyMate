package com.example.projectmoneymate.data

import androidx.compose.ui.graphics.Color

enum class Category(val title: String, val emoji: String, val color: Color) {
    FOOD("Food", "🍔", Color(0xFFFF8A65)),
    TRANSPORT("Transport", "🚕", Color(0xFF4FC3F7)),
    SHOPPING("Shopping", "🛍️", Color(0xFFBA68C8)),
    BILLS("Bills", "💡", Color(0xFFFFD54F)),
    FUN("Fun", "🎮", Color(0xFF81C784)),
    OTHER("Other", "📦", Color(0xFF90A4AE))
}