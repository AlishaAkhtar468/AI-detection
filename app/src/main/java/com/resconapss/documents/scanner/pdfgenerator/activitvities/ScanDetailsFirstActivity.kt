package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmap
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.rotateBitmap

class ScanDetailsFirstActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // Retrieve image from intent
       /* val imageByteArray = intent.getByteArrayExtra("image")
        if (imageByteArray != null) {
            bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)

        }*/

        val imageUriString = intent.getStringExtra("image_uri")
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            val inputStream = contentResolver.openInputStream(imageUri)
            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            }
            Log.d("Image", "Bitmap Image Received ")
        }

        setContent {
            DocumentScannerAITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    ScanDetailsFirst(this, bitmap)
                }
            }
        }
    }


    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun Scanpreview1() {
        DocumentScannerAITheme {
            ScanDetailsFirst(this, bitmap)
        }
    }
}