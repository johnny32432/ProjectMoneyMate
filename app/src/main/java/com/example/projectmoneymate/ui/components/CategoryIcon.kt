package com.example.projectmoneymate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectmoneymate.data.Category

@Composable
fun CategoryIcon(category: Category, size: Dp = 44.dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .background(category.color.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category.emoji, fontSize = (size.value * 0.45f).sp)
    }
}