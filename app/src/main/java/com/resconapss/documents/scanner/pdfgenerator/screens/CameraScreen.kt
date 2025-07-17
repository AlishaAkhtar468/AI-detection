package com.resconapss.documents.scanner.pdfgenerator.screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.resconapss.documents.scanner.pdfgenerator.activitvities.ScanDetailsFirstActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.ShowImagePagerActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.setFlashLight
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler


@Composable
fun CameraScreen(context:Activity) {
    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    var isBoxVisible by remember { mutableStateOf(false) }
    var isFlashOn by remember { mutableStateOf(false) }
    var modedecider by remember { mutableStateOf(0) }
    var doc_capture_count by remember { mutableStateOf(0) }
    mycontext=context
    Box(modifier = Modifier
        .fillMaxSize()
    )
    {
        val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris ->
                selectedImageUris = uris

                // Start the new Activity and pass the list of URIs
                val intent = Intent(context, ScanDetailsFirstActivity::class.java).apply {
                    putParcelableArrayListExtra("imageUris", ArrayList(uris))
                }
                context.startActivity(intent)
            }
        )

        Column(modifier=Modifier.fillMaxSize()) {
            val imageRes = if (isFlashOn) R.drawable.flashon else R.drawable.flashoff
            val painter: Painter = painterResource(id = imageRes)
            //header
            Box(modifier = Modifier
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
                            // Handle click event and navigate to next activity
                            context.finish()
                        },

                    )

                Image(

                    painter = painter, // Replace with your icon resource
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(25.dp)
                        .align(AbsoluteAlignment.CenterRight)
                        .padding(end = 10.dp)
                        .clickable {
                            isFlashOn = !isFlashOn
                            setFlashLight(context, isFlashOn)
                        }

                )


            }

            //camview
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(3.75f)
                .background(Color.Transparent)
            )
            {

            }
            //selection

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f)
                    .background(colorResource(id = R.color.naveyblue))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)

                ) {
                    if (isBoxVisible) {

                        MyWidgetsFunctions.doc_view("Single Doc",R.drawable.singledoc, onClick ={
                            isBoxVisible = false
                            Toast.makeText(context,"Single Doc Mood Selected",Toast.LENGTH_SHORT).show()
                            modedecider=1

                        })
                        MyWidgetsFunctions.doc_view("Multiple Doc",R.drawable.doubledoc, onClick =
                        {
                            isBoxVisible = false
                            Toast.makeText(context,"Multiple Doc Mood Selected",Toast.LENGTH_SHORT).show()
                            modedecider=2

                        })
                    }

                }

            }


            //types
            Box(modifier = Modifier
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
                            .padding(15.dp),
                        contentAlignment = Alignment.Center // Center the content inside the Box
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxSize(),
                            onClick = {
                                isBoxVisible = !isBoxVisible },
                            colors = ButtonDefaults.buttonColors(
                                colorResource(id =R.color.lightblue ))
                        )
                        {
                            Text(text = "Documents")
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                        contentAlignment = Alignment.Center

                    ){
                        Button(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            onClick = {


                            },
                            colors = ButtonDefaults.buttonColors(
                                colorResource(id =R.color.lightblue ).copy(alpha = 0.5f))
                        )
                        {
                            Text(text = "ID Card")
                        }
                    }
                }

            }
            //capturelay
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(colorResource(id = R.color.naveyblue)))
            {
                Row(modifier = Modifier.fillMaxSize()) {
                    //select from gallery button
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable {
                              Config.gallerycamdecider=1
                               multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ){

                            Image(
                                painter = painterResource(id = R.drawable.opengallery), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp))
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = "Take From Gallery",
                                color = colorResource(id = R.color.white),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    //picture capture button
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable {
                        if (modedecider>0)
                        {
                            if (modedecider==1)
                            {
                                CameraScanActivity.captureImage(context)
                                Config.pagemode=1
                            }
                            else{

                                doc_capture_count++
                                Config.pagemode=doc_capture_count
                            }
                        }
                            else
                        {
                            Toast.makeText(context,"Click on doc selection button to select capture mode",Toast.LENGTH_SHORT).show()
                        }



                        },)
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
                                    .padding(15.dp))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp), // Dark blue background
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                                        .padding(2.dp)
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.pagethumb), // Replace with your icon resource
                                            contentDescription = null,
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight()
                                        )

                                        if(modedecider==2)
                                        {
                                            IconButton(
                                                onClick = {

                                                    CameraScanActivity.captureImage(context)
                                                },
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .padding(end = 1.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.forword), // Replace with your forward icon resource
                                                    contentDescription = "Forward",
                                                    tint = Color.Red
                                                )
                                            }
                                        }

                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .offset(x = 0.dp, y = (-8).dp)
                                        .size(40.dp)
                                        .background(Color.Red, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$doc_capture_count",
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













