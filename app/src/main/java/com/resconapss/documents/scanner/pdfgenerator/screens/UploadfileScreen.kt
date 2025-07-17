package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
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
import com.resconapss.documents.scanner.pdfgenerator.activitvities.UploadingFileActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler

@Composable
fun  UploadfileScreen(context:Activity) {

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier.fillMaxSize()) {
          
            MyWidgetsFunctions.my_custom_toolbar(title = stringResource(id = R.string.cloudbackup), context =context )

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                    Column {
                        Image(painter = painterResource(R.drawable.emptybox),
                            contentDescription ="create",
                            modifier = Modifier.clickable {})
                        UsableWidgets.spacer1(mymodifier = Modifier
                            .wrapContentWidth()
                            .height(5.dp))

                        Text(
                            text = "No file Uploaded yet",
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontFamily = popinreguler,
                            fontWeight = FontWeight.Normal,

                            )
                    }

                }


            }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .align(Alignment.BottomCenter))
        {
            UsableWidgets.layout_oneimagetext(title = stringResource(id = R.string.uploadfile), imageid =R.drawable.createfoldericon ) {


                val intent=Intent(context,UploadingFileActivity::class.java)
                context.startActivity(intent)
            }
        }
    }


}