package com.example.ia08

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

enum class StrokeStyle {
    Solid,
    Dashed,
    Dotted
}

data class Stroke(
    val path: Path = Path(),
    val color: Color,
    val width: Float,
    val style: StrokeStyle = StrokeStyle.Solid
)

