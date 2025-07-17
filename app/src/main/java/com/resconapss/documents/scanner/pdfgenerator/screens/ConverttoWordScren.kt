package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets.Companion.DocumentCard
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets.Companion.document
import com.resconapss.documents.scanner.pdfgenerator.models.mydocument

@Composable
fun ConverttoWordScren(context: Activity) {

    Box(modifier = Modifier.fillMaxSize())
    {
        Column {
            MyWidgetsFunctions.my_custom_toolbar(title = stringResource(id = R.string.toword), context = context)
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(20.dp))
            Text(text = "Choose your files that you want to converted into world press files \n" +
                        "in high quality",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp))
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(20.dp))


            LazyColumn {
                items(document) { document ->
                    DocumentCard(document)
                }
            }


        }
        Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(Color.White).align(Alignment.BottomCenter))
        {
            MyWidgetsFunctions.generalbutton(title = "Export Document") {

        }

        }

    }
}