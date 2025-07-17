package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.dialogs.DeleteDialog
import com.resconapss.documents.scanner.pdfgenerator.dialogs.SuccessDialog
import com.resconapss.documents.scanner.pdfgenerator.dialogs.SuccessfullDeleteDialog
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.models.document_item

@Composable
fun DeleteCustomFolderScreen(context:Activity) {

    var cratedilog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val mydocuments = listOf(
        document_item(1, "Document000012", "10/20/2023", "10:40:00PM"),
        document_item(2, "Document000012", "10/20/2023", "10:40:00PM"),
        document_item(3, "Document000012", "10/20/2023", "10:40:00PM"),
        document_item(4, "Document000012", "10/20/2023", "10:40:00PM"),


        )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column(modifier = Modifier.fillMaxSize()) {
            MyWidgetsFunctions.my_custom_toolbar(title = stringResource(id = R.string.customfolder), context =context )
            MyWidgetsFunctions.bannerlay()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))
            MyWidgetsFunctions.CustomDeleteDocumentList(documents =mydocuments,context ,showDialog)


        }
        if(showDialog)
        {
            Column(modifier= Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomEnd))
            {


               // DeleteDialog(showDialog = showDialog, onDismiss = { showDialog = false },context)
                SuccessfullDeleteDialog(showDialog = showDialog, onDismiss = { showDialog = false },context)

            }
        }
    }


}