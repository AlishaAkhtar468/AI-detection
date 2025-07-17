package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.screens.ScanDetailsFirst
import com.resconapss.documents.scanner.pdfgenerator.screens.ScanDetailsSecond
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmap
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.rotateBitmap

class ScanDetailsSecondActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Retrieve image from intent
        val imageByteArray = intent.getByteArrayExtra("image")
        if (imageByteArray != null) {
            bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)

        }

        setContent {
            DocumentScannerAITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    ScanDetailsSecond(this)
                }
            }
        }
    }


    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun Scanpreview() {
        DocumentScannerAITheme {
            ScanDetailsSecond(this)
        }
    }
}