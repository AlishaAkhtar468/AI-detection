package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraScanActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraView
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
import com.resconapss.documents.scanner.pdfgenerator.adapters.ViewPagerWithImages
import com.resconapss.documents.scanner.pdfgenerator.adapters.galleryviewpager
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler

@Composable
fun ShowDocument(myBitmap: Bitmap) {
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
fun ScanDetailsFirst(context: Activity, bitmap: Bitmap?) {
    mycontext = context
    // Get the list of image URIs from the intent
    val imageUris = context.intent.getParcelableArrayListExtra<Uri>("imageUris")
    var bitmap by remember { mutableStateOf(Config.bitmap) }
    var showFilters by remember { mutableStateOf(false) }


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
                    title = "Details",
                    button_title = "Next",
                    context = context
                ) {
                    Toast.makeText(context, "clickable", Toast.LENGTH_SHORT).show()
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

                if (Config.gallerycamdecider == 1) {
                    galleryviewpager(context = context)
                } else {
                    ViewPagerWithImages(bitmap = bitmap)
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
                    /*Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                bitmap?.let {
                                    bitmap = Config.rotateBitmap(it, 270f)
//                                    Toast
//                                        .makeText(context, "Rotate click", Toast.LENGTH_SHORT)
//                                        .show()
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
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }*/

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                showFilters = !showFilters
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.adfilters), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = stringResource(id = R.string.filters),
                                color = colorResource(id = R.color.black),
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
//                                    Toast
//                                        .makeText(context, "Rotate click", Toast.LENGTH_SHORT)
//                                        .show()
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

                                bitmap = Config.rotateBitmap(it, 180f) // Rotate right logic
//                                Toast
//                                    .makeText(context, "Rotate click", Toast.LENGTH_SHORT)
//                                    .show()
                            }
                        }
                        .fillMaxHeight()
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.allrotate),
                                // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = (stringResource(id = R.string.rotateall)),
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
                                val intent = Intent(context, CameraView::class.java)
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

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.scanagaiin), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(top = 5.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = (stringResource(id = R.string.scanagain)),
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    // Filters Panel
                    if (showFilters) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                                .padding(8.dp)
                        ) {
                            Column {
                                // Close Button
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close Filter Panel",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clickable { showFilters = false }
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    FilterButton("Grayscale") {
                                        bitmap?.let {
                                            bitmap = applyGrayscaleFilter(it)
                                        }
                                    }
                                    FilterButton("Sepia") {
                                        bitmap?.let {
                                            bitmap = applySepiaFilter(it)
                                        }
                                    }
                                    FilterButton("Invert") {
                                        bitmap?.let {
                                            bitmap = applyInvertFilter(it)
                                        }
                                    }
                                    // Add more filters here
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

        @Composable
        fun FilterButton(filterName: String, onApplyFilter: () -> Unit) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onApplyFilter() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = filterName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

fun applyGrayscaleFilter(bitmap: Bitmap): Bitmap {

    return bitmap
}

fun applySepiaFilter(bitmap: Bitmap): Bitmap {

    return bitmap
}

fun applyInvertFilter(bitmap: Bitmap): Bitmap {

    return bitmap
}