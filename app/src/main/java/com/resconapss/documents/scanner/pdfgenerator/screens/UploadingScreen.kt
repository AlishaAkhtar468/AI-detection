package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler


@Composable
fun UploadingScreen(context:Activity) {

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier.fillMaxSize()) {
            
            MyWidgetsFunctions.my_custom_toolbar(title = stringResource(id = R.string.uploadtocloud), context =context )
            Column(Modifier.fillMaxSize()) {
                UsableWidgets.spacer1(mymodifier = Modifier.wrapContentWidth().height(15.dp))
                Box(modifier = Modifier.fillMaxWidth().weight(1.5f))
                {
                    Image(painter = painterResource(R.drawable.cloudloading),
                        contentDescription ="create",
                        modifier = Modifier.padding(20.dp).clickable {})

                }
                Box(modifier = Modifier.fillMaxWidth().weight(0.5f), contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Please wait file is uploading....",
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
                Box(modifier = Modifier.fillMaxWidth().weight(1f))
                { Image(painter = painterResource(R.drawable.nativeadlayout),
                    contentDescription ="create",
                    modifier = Modifier.padding(20.dp).clickable {})

                }

            }
        }
    }
}