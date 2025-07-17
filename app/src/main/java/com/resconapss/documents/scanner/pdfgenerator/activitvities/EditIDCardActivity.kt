package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.resconapss.documents.scanner.pdfgenerator.screens.EditCardScreen
import com.resconapss.documents.scanner.pdfgenerator.screens.ScanDetailsSecond
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmap

class EditIDCardActivity : ComponentActivity() {
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

                   EditCardScreen(this)
                }
            }
        }
    }


    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun Scanpreview() {
        DocumentScannerAITheme {
            EditCardScreen(this)
        }
    }
}