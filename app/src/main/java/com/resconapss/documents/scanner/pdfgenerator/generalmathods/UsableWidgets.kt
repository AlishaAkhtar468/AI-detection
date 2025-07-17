package com.resconapss.documents.scanner.pdfgenerator.generalmathods

import android.app.Activity
import android.content.Intent
import android.service.autofill.OnClickAction
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CreateFolderActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.MoreToolsActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.SettingActivity
import com.resconapss.documents.scanner.pdfgenerator.models.mydocument
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinbold
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler


class UsableWidgets {
    companion object{
        val document = listOf(
        mydocument("Document000012", "10/20/2023", "10:40:00PM", R.drawable.docthumb),
            mydocument("Document000012", "10/20/2023", "10:40:00PM", R.drawable.docthumb),
         mydocument("Document000012", "10/20/2023", "10:40:00PM", R.drawable.docthumb),
         mydocument("Document000012", "10/20/2023", "10:40:00PM", R.drawable.docthumb),
         mydocument("Document000012", "10/20/2023", "10:40:00PM", R.drawable.docthumb)




        )
        //Home top app bar
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun TopBar(context: Activity) {

            TopAppBar(
                title = {
                    Column(modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()) {
                        Text(
                            "All Document Scanner",
                            fontWeight = FontWeight.Bold,
                            fontFamily = popinbold,
                            color = Color.Black // Set the desired text color here
                        )
                        Text("ID Card Scanner & Documents Scanner",
                            fontSize = 12.sp,
                            fontFamily = popinreguler,
                            color = colorResource(id = R.color.lightblue)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.sky), // Replace with your desired background color resource
                    titleContentColor = Color.White, // Replace with your desired content color
                    actionIconContentColor = Color.White // Replace with your desired action icon color
                ),
                actions = {
                    IconButton(onClick = {  val intent=Intent(context, MoreToolsActivity::class.java)
                        context.startActivity(intent) }) {
                        Image(
                            painter = painterResource(id = R.drawable.tools),  // Replace with your drawable resource ID
                            contentDescription = "Profile",

                        )
                    }
                    IconButton(onClick = {
                        val intent = Intent(context, SettingActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.mysettings),  // Replace with your drawable resource ID
                            contentDescription = "Profile",
                            //colorFilter = ColorFilter.tint(Color.White) // Set the desired icon color here
                        )
                    }
                }
            )
        }

        //spacer1
        @Composable
        fun spacer1(mymodifier: Modifier)
        {
            Spacer(modifier =mymodifier)
        }
        @Composable
        fun layout_with_oneimage_twotext(context: Activity,imgid:Int,title1:String,title2:String, onClick: () -> Unit)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(20.dp)
                    .clickable(
                        onClick = onClick
                    )
                    .background(
                        colorResource(id = R.color.neela),
                        shape = RoundedCornerShape(5.dp)
                    ))
            {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .background(
                        colorResource(id = R.color.white),
                        shape = RoundedCornerShape(5.dp)
                    )
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f), contentAlignment =  Alignment.Center){

                        Image(painter = painterResource(id = imgid),
                            contentDescription ="create",
                            modifier = Modifier.clickable {

                            }

                        )
                    }
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1.5f), contentAlignment = Alignment.Center)
                    {
                        Column {
                            Text(

                                text = title1,
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinbold,
                                fontSize = 17.sp,
                                textAlign = TextAlign.Left
                            )

                            Text(

                                text = title2,
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Left
                            )

                        }
                    }





                }

            }
        }

        //layout with one image and text
        @Composable
        fun layout_oneimagetext(title: String,imageid:Int,onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                    .clickable(
                        onClick = onClick
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(imageid), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }
        @Composable
        fun DocumentCard(document: mydocument) {
            var checked by remember { mutableStateOf(false) }
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(0xffffffff))
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(document.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(end = 8.dp)
                        )
                        Column {
                            Text(
                                text = document.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Date: ${document.date}  Time: ${document.time}",
                                fontSize = 14.sp
                            )
                        }
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                    }
                }

            }
        }


    }
}