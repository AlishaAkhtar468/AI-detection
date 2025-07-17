package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraScanActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
import com.resconapss.documents.scanner.pdfgenerator.adapters.ViewPagerWithImages
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler


@Composable
fun EditCardScreen(context: Activity) {
    mycontext = context

    var bitmap by remember { mutableStateOf(Config.bitmap) }

    var mypageno by remember { mutableStateOf(0) }
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
                    title = stringResource(id = R.string.details),
                    button_title = stringResource(id = R.string.done),
                    context = context
                ) {
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
                    .weight(2.7f)
            ) {


               /* bitmap?.let {
                    ShowDocument(myBitmap = it)
                }*/
                ViewPagerWithImages(bitmap = bitmap)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f)
            )
            {

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.sky))
                    .weight(0.5f)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .clickable {
                            bitmap?.let {

                                bitmap = Config.rotateBitmap(it, 180f) // Rotate right logic
                                Toast
                                    .makeText(context, "Rotate click", Toast.LENGTH_SHORT)
                                    .show()
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
                                painter = painterResource(id = R.drawable.retake),
                                // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp))
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = (stringResource(id = R.string.retake)),
                                color = colorResource(id = R.color.dimblue),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                bitmap?.let {
                                    bitmap = Config.rotateBitmap(it, 270f)
                                    Toast
                                        .makeText(context, "Rotate click", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.leftrotate), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = stringResource(id = R.string.rotateleft),
                                color = colorResource(id = R.color.dimblue),  // Adjust text color as needed
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
                                bitmap?.let {

                                    bitmap = Config.rotateBitmap(it, -270f) // Rotate right logic
                                    Toast
                                        .makeText(context, "Rotate click", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.rightrotate), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = stringResource(id = R.string.rotateright),
                                color = colorResource(id = R.color.dimblue),  // Adjust text color as needed
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
                                painter = painterResource(id = R.drawable.autodetect), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp))
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = (stringResource(id = R.string.autoselect)),
                                color = colorResource(id = R.color.dimblue),  // Adjust text color as needed
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
    }
}








