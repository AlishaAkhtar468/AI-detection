package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.resconapss.documents.scanner.pdfgenerator.screens.CreateFolderScreen
import com.resconapss.documents.scanner.pdfgenerator.screens.UploadfileScreen
import com.resconapss.documents.scanner.pdfgenerator.screens.UploadingScreen
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme

class UploadFileActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DocumentScannerAITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    UploadfileScreen(this)
                }
            }
        }
    }


    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun upload_preview() {
        DocumentScannerAITheme {
          UploadfileScreen(this)


        }
    }
}