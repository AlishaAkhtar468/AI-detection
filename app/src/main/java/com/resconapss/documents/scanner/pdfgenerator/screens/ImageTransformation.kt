package com.resconapss.documents.scanner.pdfgenerator.screens

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

class ImageTransformation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val byteArray = intent.getByteArrayExtra("full_image")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        val screenWidth = intent.getFloatExtra("screen_width", bitmap.width.toFloat())
        val screenHeight = intent.getFloatExtra("screen_height", bitmap.height.toFloat())

        val q1Array = intent.getFloatArrayExtra("q1")
        val q2Array = intent.getFloatArrayExtra("q2")
        val q3Array = intent.getFloatArrayExtra("q3")
        val q4Array = intent.getFloatArrayExtra("q4")

        setContent {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Full Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillHeight // ✅ fills height exactly, width adjusts proportionally
                )

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    // Calculate scale to fit height exactly
                    val scale = canvasHeight / screenHeight

                    // Calculate new image width after scaling
                    val imageWidthScaled = screenWidth * scale

                    // Calculate horizontal offset to center image
                    val offsetX = (canvasWidth - imageWidthScaled) / 2f

                    // Adjust detection points using scale and offset
                    val q1 = Offset(q1Array!![0] * scale + offsetX, q1Array[1] * scale)
                    val q2 = Offset(q2Array!![0] * scale + offsetX, q2Array[1] * scale)
                    val q3 = Offset(q3Array!![0] * scale + offsetX, q3Array[1] * scale)
                    val q4 = Offset(q4Array!![0] * scale + offsetX, q4Array[1] * scale)

                    // Draw detection box
                    drawCircle(Color.Red, radius = 10f, center = q1)
                    drawCircle(Color.Red, radius = 10f, center = q2)
                    drawCircle(Color.Red, radius = 10f, center = q3)
                    drawCircle(Color.Red, radius = 10f, center = q4)

                    drawLine(Color.Green, q1, q2, strokeWidth = 5f)
                    drawLine(Color.Green, q2, q3, strokeWidth = 5f)
                    drawLine(Color.Green, q3, q4, strokeWidth = 5f)
                    drawLine(Color.Green, q4, q1, strokeWidth = 5f)
                }
            }
        }
    }
}
