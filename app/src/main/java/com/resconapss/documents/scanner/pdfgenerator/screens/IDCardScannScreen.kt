package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraScanActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.IDCardScanActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.ScanDetailsFirstActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.setFlashLight
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext


@Composable
fun IDCardScannScreen(context:Activity) {
    var isBoxVisible by remember { mutableStateOf(false) }
    var isidcardside by remember { mutableStateOf(false) }
    var text_button by remember { mutableStateOf("ID Card") }
    var text_docside by remember { mutableStateOf("Front Side") }
    var doc_capture_count by remember { mutableStateOf(0) }
    var isFlashOn by remember { mutableStateOf(false) }

    mycontext = context


    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
            val imageRes = if (isFlashOn) R.drawable.flashon else R.drawable.flashoff
            val painter: Painter = painterResource(id = imageRes)
            //header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)

            )
            {
                Image(

                    painter = painterResource(id = R.drawable.backarrow), // Replace with your icon resource
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(25.dp)
                        .align(AbsoluteAlignment.CenterLeft)
                        .padding(start = 10.dp)
                        .clickable {

                            context.finish()
                        }

                    )

                Image(

                    painter = painter, // Replace with your icon resource
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(25.dp)
                        .align(AbsoluteAlignment.CenterRight)
                        .padding(end = 10.dp) .clickable {
                            isFlashOn = !isFlashOn
                            setFlashLight(context, isFlashOn)
                        }

                )


            }

            //camview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.75f)
                    .background(Color.Transparent)
            )
            {
                if (isidcardside) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.foucus_bar), // Replace with your icon resource
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp)
                        )


                    }

                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                    {

                        MyWidgetsFunctions.side_change_text(title = text_docside)
                    }
                }

            }
            //selection

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.65f)
                    .background(colorResource(id = R.color.naveyblue))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)

                ) {
                    if (isBoxVisible) {


                        MyWidgetsFunctions.doc_view("ID Card", R.drawable.singledoc, onClick = {
                            isidcardside = true
                            text_button = "ID Card"
                            //Toast.makeText(context,"Click on Single View",Toast.LENGTH_SHORT).show()
                        })
                        MyWidgetsFunctions.doc_view("Driving Licence",
                            R.drawable.doubledoc,
                            onClick =
                            {
                                isidcardside = true
                                text_button = "Driving Licence"
                                // Toast.makeText(context,"Click on Multiple View",Toast.LENGTH_SHORT).show()
                            })

                        MyWidgetsFunctions.doc_view("Passport", R.drawable.doubledoc, onClick =
                        {
                            isidcardside = true
                            text_button = "Passport"

                            // Toast.makeText(context,"Click on Multiple View",Toast.LENGTH_SHORT).show()
                        })
                    }

                }

            }


            //types
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .background(colorResource(id = R.color.naveyblue))
            )
            {
                Row(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center // Center the content inside the Box
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxSize(),
                            onClick = { /* Handle button click */ },
                            colors = ButtonDefaults.buttonColors(
                                colorResource(id = R.color.lightblue).copy(alpha = 0.5f)
                            )
                        )
                        {
                            Text(text = "Documents")
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        contentAlignment = Alignment.Center

                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            onClick = {
                                Toast.makeText(context, "Clickable", Toast.LENGTH_SHORT).show()
                                isBoxVisible = !isBoxVisible

                            },
                            colors = ButtonDefaults.buttonColors(
                                colorResource(id = R.color.lightblue)
                            )
                        )
                        {
                            Text(text = text_button)
                        }
                    }
                }

            }
            //capturelay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .background(colorResource(id = R.color.naveyblue))
            )
            {

                Row(modifier = Modifier.fillMaxSize()) {

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable {

                        }
                    )
                    {
                        Column(

                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {

                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable {
                                if (isidcardside) {
                                    doc_capture_count++
                                    if (doc_capture_count == 1) {
                                        text_docside = "Back Side"

                                    } else if (doc_capture_count > 1) {
                                        IDCardScanActivity.captureImage(context)
                                        doc_capture_count = 0
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "First select card Type click on id card button",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            },
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.cambutton), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        )
                        {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    // Dark blue background
                                    .padding(20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                                        .padding(2.dp)
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) {
                                    Column {
                                        // Simulate document text
                                        Image(
                                            painter = painterResource(id = R.drawable.pagethumb), // Replace with your icon resource
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                        )

                                        // More text or content can be added here
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .offset(x = 8.dp, y = (-8).dp)
                                        .size(40.dp)
                                        .background(Color.Red, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "" + doc_capture_count,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                            }


                        }
                    }


                }
            }

        }


    }
}










