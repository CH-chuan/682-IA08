package com.example.ia08

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToolPanel(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    brushSize: Float,
    onBrushSizeChanged: (Float) -> Unit,
    currentStrokeStyle: StrokeStyle,
    onStrokeStyleChanged: (StrokeStyle) -> Unit,
    onClearCanvas: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.3f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Color Picker & Eraser
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val colors = listOf(Color.Black, Color.Red, Color.Blue, Color.Green, Color.Yellow)
            colors.forEach { color ->
                ColorButton(
                    color = color,
                    isSelected = color == selectedColor,
                    onClick = { onColorSelected(color) }
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Eraser Button
            Button(
                onClick = { onColorSelected(Color.White) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedColor == Color.White) Color.Gray else Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Eraser")
            }
        }

        // Brush Size Slider
        Text("Brush Size: ${brushSize.toInt()}")
        Slider(
            value = brushSize,
            onValueChange = onBrushSizeChanged,
            valueRange = 5f..50f,
            modifier = Modifier.fillMaxWidth()
        )

        // Pattern Selector
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            StrokeStyle.values().forEach { style ->
                Button(
                    onClick = { onStrokeStyleChanged(style) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentStrokeStyle == style) Color.DarkGray else Color.LightGray
                    )
                ) {
                    Text(style.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Clear Button
        Button(onClick = onClearCanvas) {
            Text("Clear Canvas")
        }
    }
}

@Composable
fun ColorButton(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color)
            .clickable(onClick = onClick)
            .then(
                if (isSelected) {
                    Modifier.padding(2.dp) // Visual indicator for selection could be improved
                } else {
                    Modifier
                }
            )
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

