package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.PremiumOfferSection
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.RecentFilesSection
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.ScannerOptions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.TopBar
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.bannerlay
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.cameraPermissionGranted
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
import kotlinx.coroutines.flow.MutableStateFlow




@Composable
fun HomeScreen(context:Activity) {


    mycontext=context





    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(context)
        bannerlay()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {

            item {

                PremiumOfferSection(context)
            }
            item {
                ScannerOptions(context)

            }

        }
        RecentFilesSection()




    }



}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun DocumentScannerAppPreview() {
   HomeScreen(context = mycontext )
}


