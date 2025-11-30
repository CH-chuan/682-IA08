package com.example.ia08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.example.ia08.ui.theme.IA08Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IA08Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DrawingApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DrawingApp(modifier: Modifier = Modifier) {
    // State for strokes
    val strokes = remember { mutableStateListOf<Stroke>() }
    
    // State for current stroke being drawn
    var currentStroke by remember { mutableStateOf<Stroke?>(null) }
    
    // State for tool panel
    var selectedColor by remember { mutableStateOf(Color.Black) }
    var brushSize by remember { mutableFloatStateOf(10f) }
    var currentStrokeStyle by remember { mutableStateOf(StrokeStyle.Solid) }

    Box(modifier = modifier.fillMaxSize()) {
        DrawingCanvas(
            strokes = strokes,
            currentStroke = currentStroke,
            onStrokeStart = { x, y ->
                val path = Path().apply { moveTo(x, y) }
                currentStroke = Stroke(path, selectedColor, brushSize, currentStrokeStyle)
            },
            onStrokeDrag = { x, y ->
                currentStroke?.path?.lineTo(x, y)
                currentStroke = currentStroke?.copy() 
            },
            onStrokeEnd = {
                currentStroke?.let { strokes.add(it) }
                currentStroke = null
            },
            modifier = Modifier.background(Color.White)
        )

        ToolPanel(
            selectedColor = selectedColor,
            onColorSelected = { selectedColor = it },
            brushSize = brushSize,
            onBrushSizeChanged = { brushSize = it },
            currentStrokeStyle = currentStrokeStyle,
            onStrokeStyleChanged = { currentStrokeStyle = it },
            onClearCanvas = { strokes.clear() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}