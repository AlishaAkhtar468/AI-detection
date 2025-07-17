package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.resconapss.documents.scanner.pdfgenerator.screens.Introscreen
import com.resconapss.documents.scanner.pdfgenerator.screens.OnboardingScreen
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme

class IntroActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DocumentScannerAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                   Introscreen(this)


                    }
                }
            }
        }


    @Preview(showBackground = true, device = Devices.PIXEL_4)
    @Composable
    fun Preview() {
        DocumentScannerAITheme {

            Introscreen(this)

        }
    }



}










