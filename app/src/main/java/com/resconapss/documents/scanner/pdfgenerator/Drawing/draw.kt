package com.resconapss.documents.scanner.pdfgenerator.Drawing

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun DrawFunction(k1: Offset, k2: Offset, k3: Offset, k4: Offset) {
    // Initial points
    var p1 by remember { mutableStateOf(Offset(0f, 0f)) }
    var p2 by remember { mutableStateOf(Offset(0f, 0f)) }
    var p3 by remember { mutableStateOf(Offset(0f, 0f)) }
    var p4 by remember { mutableStateOf(Offset(0f, 0f)) }

    val pointsP = arrayOf(p1, p2, p3, p4)
    var pointsQ by remember { mutableStateOf(arrayOf(k1, k2, k3, k4)) }

    // Animatable instances for each point
    val animatedPoints = remember {
        pointsP.map { point ->
            Animatable(point.x) to Animatable(point.y)
        }    }

    // Animation specification

    val animationSpec = tween<Float>(durationMillis = 800)

    LaunchedEffect(k1, k2, k3, k4) {
        pointsQ = arrayOf(k1, k2, k3, k4)
        // Launch animations for all points at the same time
        animatedPoints.forEachIndexed { index, (animatableX, animatableY) ->
            val targetPoint = pointsQ[index]
            launch {
                animatableX.animateTo(targetPoint.x, animationSpec)
            }
            launch {
                animatableY.animateTo(targetPoint.y, animationSpec)
            }
        }

        // Wait for the animation to complete
        delay(animationSpec.durationMillis.toLong())
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        animatedPoints.forEachIndexed { index, (animatableX, animatableY) ->
            val point = Offset(animatableX.value, animatableY.value)
            val nextIndex = (index + 1) % animatedPoints.size
            val nextPoint = Offset(animatedPoints[nextIndex].first.value, animatedPoints[nextIndex].second.value)

            // Draw line between current point and next point
            drawLine(
                color = Color.Blue,
                start = point,
                end = nextPoint,
                strokeWidth = 12f
            )

            // Draw the circle
            drawCircle(
                color = Color.Red,
                center = point,
                radius = 8f
            )
        }
    }
}