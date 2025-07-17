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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.resconapss.documents.scanner.pdfgenerator.dialogs.SuccessDialog
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.DocumentList
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.generalbutton
import com.resconapss.documents.scanner.pdfgenerator.models.document_item
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmap
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext



@Composable
fun CreateFolderScreen(context: Activity) {
    mycontext = context

    
    var cratedilog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val documents = listOf(
        document_item(1, "Document000012", "10/20/2023", "10:40:00PM"),
        document_item(2, "Document000012", "10/20/2023", "10:40:00PM"),
        document_item(3, "Document000012", "10/20/2023", "10:40:00PM"),
        document_item(4, "Document000012", "10/20/2023", "10:40:00PM"),


        )

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

                MyWidgetsFunctions.my_customtoolbarwith_button(
                    title = stringResource(id = R.string.createfolder),
                    button_title = stringResource(id = R.string.seeall),
                    context = context
                ) {

                }

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

                DocumentList(documents)

            }

            Box(
                modifier = Modifier
                    .weight(0.4f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.sky))
                    .weight(0.35f)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    {
                        MyWidgetsFunctions.generalbutton(title = stringResource(id = R.string.cancel)) {

                            context.finish()
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    {
                      MyWidgetsFunctions.generalbutton(title = stringResource(id = R.string.create)) {

                          showDialog=true
                         

                      }
                    }

                }
                }
            }

        if(showDialog)
        {
            Column(modifier= Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomEnd))
            {


                SuccessDialog(showDialog = showDialog, onDismiss = { showDialog = false },context)

            }
        }

        }

}











