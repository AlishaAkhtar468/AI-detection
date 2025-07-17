package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraScanActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmap
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
@Composable
fun Show_image(myBitmap: Bitmap) {
    val imageBitmap = myBitmap.asImageBitmap()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun ExporttoDocScreen(context: Activity) {
    mycontext = context

    var bitmap by remember { mutableStateOf(Config.bitmap) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .background(colorResource(id = R.color.sky))
            ) {
                MyWidgetsFunctions.my_custom_toolbar(title = stringResource(id = R.string.discription), context = context)

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            ) {
                // Add any additional UI elements if needed
            }

            Box(
                modifier = Modifier
                    .weight(2.7f)
            ) {
                bitmap?.let {
                    ShowDocument(myBitmap = it)
                }
            }

            Box(
                modifier = Modifier
                    .weight(0.2f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.sky))
                    .weight(0.5f)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {

                    MyWidgetsFunctions.ExportDpcument{

                    }
                }
            }
        }
    }
}








