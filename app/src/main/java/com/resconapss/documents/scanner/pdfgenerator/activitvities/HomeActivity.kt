package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

import com.resconapss.documents.scanner.pdfgenerator.screens.HomeScreen
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme
import kotlinx.coroutines.flow.MutableStateFlow

class HomeActivity : ComponentActivity() {

    private val isCameraPermissionGranted = MutableStateFlow(false)

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DocumentScannerAITheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {

                    HomeScreen(this)

                }
            }
        }
    }



    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun Preview2() {
        DocumentScannerAITheme {

            HomeScreen(this)

        }
    }
}

