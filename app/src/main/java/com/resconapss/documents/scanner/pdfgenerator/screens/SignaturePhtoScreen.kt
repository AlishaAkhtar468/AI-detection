package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinbold
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler

@Composable
fun SignaturePhtoScreen (context:Activity){

    Box(modifier = Modifier.fillMaxSize())
    {

        Column(modifier=Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .background(colorResource(id = R.color.sky))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backblackarrow), // Replace with your icon resource
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(25.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                        .clickable {
                            // Handle click event and navigate to next activity
                            context.finish()
                        }
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    text = stringResource(R.string.signature),
                    fontSize = 20.sp,
                    fontFamily = popinbold,
                    color = colorResource(id = R.color.black)
                )
                Button(
                    modifier = Modifier
                        .width(100.dp) // Adjusted width
                        .height(40.dp)
                        .padding(end = 10.dp)
                        .align(Alignment.CenterEnd),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.lightblue))
                ) {
                    Text(
                        text = stringResource(R.string.done),
                        fontSize = 12.sp, // Adjusted font size
                        fontFamily = popinreguler,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
               )


            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
               )
            {
                Image(
                    painter = painterResource(id = R.drawable.mydocthumb),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth().padding(start = 5.dp, end = 5.dp)
                .weight(0.3f).background(colorResource(id = R.color.sky))
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth().align(AbsoluteAlignment.CenterLeft)
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.takefromgallery), // Replace with your icon resource
                        contentDescription = null,
                        modifier = Modifier
                            .width(35.dp)
                            .height(25.dp)
                            .padding(start = 10.dp)
                            .clickable {
                                // Handle click event and navigate to next activity
                                context.finish()
                            }
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize().padding(start = 10.dp),
                        text = stringResource(R.string.takefromgallery),
                        fontSize = 20.sp,
                        fontFamily = popinreguler,
                        color = colorResource(id = R.color.dimblue)
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth().padding(start = 5.dp, end = 5.dp,top=5.dp)
                .weight(0.3f).background(colorResource(id = R.color.sky))
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth().align(AbsoluteAlignment.CenterLeft)
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.takefromcamera), // Replace with your icon resource
                        contentDescription = null,
                        modifier = Modifier
                            .width(35.dp)
                            .height(25.dp)
                            .padding(start = 10.dp)
                            .clickable {
                                // Handle click event and navigate to next activity
                                context.finish()
                            }
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize().padding(start = 10.dp),
                        text = stringResource(R.string.takefromcamera),
                        fontSize = 20.sp,
                        fontFamily = popinreguler,
                        color = colorResource(id = R.color.dimblue)
                    )
                }
            }
        }
    }
}