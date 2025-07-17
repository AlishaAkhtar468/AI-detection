package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.resconapss.documents.scanner.pdfgenerator.screens.CloudBackup
import com.resconapss.documents.scanner.pdfgenerator.screens.CustomFolderDetailsScreen
import com.resconapss.documents.scanner.pdfgenerator.screens.DeleteCustomFolderScreen
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme

class DeleteCustomFolderActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DocumentScannerAITheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                   DeleteCustomFolderScreen(this)

                }
            }
        }
    }






    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun custom_preview1() {
        DocumentScannerAITheme {

           DeleteCustomFolderScreen(this)
        }
    }
}
