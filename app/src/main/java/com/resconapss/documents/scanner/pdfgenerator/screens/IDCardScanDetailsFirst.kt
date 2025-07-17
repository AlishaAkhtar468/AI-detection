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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.resconapss.documents.scanner.pdfgenerator.activitvities.EditIDCardActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmap
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler


@Composable
fun IDCardScanDetailsFirst(context: Activity) {
    mycontext = context

    var bitmap by remember { mutableStateOf(Config.bitmap) }
    var showDialog by remember { mutableStateOf(false) }
    var showfilters by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .background(colorResource(id = R.color.sky))
            ) {
                MyWidgetsFunctions.my_customtoolbarwith_button(
                    title ="Details" ,
                    button_title ="Next" ,
                    context =context) {
                  context.finish()
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
                    .weight(3f)
            ) {

                Column(modifier= Modifier
                    .fillMaxSize()
                    .padding(10.dp)) {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(10.dp))
                    {

                        bitmap?.let {
                            Image(


                                // painter = painterResource(R.drawable.singledoc), // Replace with your icon resource
                                bitmap = it.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                      //  bitmap?.let { MyWidgetsFunctions.ShowDocument2(it) }

                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(10.dp)) {
                        bitmap?.let {
                            Image(

                                // painter = painterResource(R.drawable.singledoc), // Replace with your icon resource
                                bitmap = it.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }

                    }



                    }
            }

            Box(
                modifier = Modifier
                    .weight(0.6f)
            )
            {
                if(showfilters)
                {
                    MyWidgetsFunctions.show_filters()
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.sky))
                    .weight(1f)
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                showDialog = true

                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.adwatermark), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.addwatermark),
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    // Repeat for other boxes with their respective icons and texts
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                val intent=Intent(context,EditIDCardActivity::class.java)
                                context.startActivity(intent)
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.editcard), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = stringResource(id = R.string.editcard),
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Box(modifier = Modifier
                        .weight(1f)
                        .clickable {

                            showfilters=!showfilters

                        }
                        .fillMaxHeight()
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ){
                            Image(
                                painter = painterResource(id = R.drawable.adfilters),
                                // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp))
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = (stringResource(id = R.string.filters)),
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .weight(1f)
                        .clickable {
                            bitmap?.let {
                                val intent = Intent(context, CameraScanActivity::class.java)
                                context.startActivity(intent)
                                context.finish()

                            }
                        }
                        .fillMaxHeight()
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ){
                            Image(
                                painter = painterResource(id = R.drawable.help), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp))
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = (stringResource(id = R.string.help)),
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Enter Text") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Text") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        // Handle OK button click
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}










