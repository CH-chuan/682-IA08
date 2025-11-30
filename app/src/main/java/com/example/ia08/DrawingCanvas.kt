package com.example.ia08

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun DrawingCanvas(
    strokes: List<com.example.ia08.Stroke>,
    currentStroke: com.example.ia08.Stroke?,
    onStrokeStart: (Float, Float) -> Unit,
    onStrokeDrag: (Float, Float) -> Unit,
    onStrokeEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        onStrokeStart(offset.x, offset.y)
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        onStrokeDrag(change.position.x, change.position.y)
                    },
                    onDragEnd = {
                        onStrokeEnd()
                    }
                )
            }
    ) {
        // Helper function to get path effect
        fun getPathEffect(style: StrokeStyle, width: Float): PathEffect? {
            return when (style) {
                StrokeStyle.Solid -> null
                StrokeStyle.Dashed -> PathEffect.dashPathEffect(floatArrayOf(width * 3, width * 3), 0f)
                StrokeStyle.Dotted -> PathEffect.dashPathEffect(floatArrayOf(width, width * 2), 0f)
            }
        }

        // Draw existing strokes
        strokes.forEach { stroke ->
            drawPath(
                path = stroke.path,
                color = stroke.color,
                style = Stroke(
                    width = stroke.width,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = getPathEffect(stroke.style, stroke.width)
                )
            )
        }

        // Draw current stroke
        currentStroke?.let { stroke ->
            drawPath(
                path = stroke.path,
                color = stroke.color,
                style = Stroke(
                    width = stroke.width,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = getPathEffect(stroke.style, stroke.width)
                )
            )
        }
    }
}

