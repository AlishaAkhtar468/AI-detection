package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.UploadFileActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets

@Composable
fun CloudBackupHome(context:Activity) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(Modifier.fillMaxSize()) {

            UsableWidgets.TopBar(context = context)
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(30.dp))
            MyWidgetsFunctions.ScannerOptions(context = context)
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(5.dp))
            UsableWidgets.layout_with_oneimage_twotext(
                context = context,
                imgid = R.drawable.uploadcloud,
                title1 ="Document Cloud Backup" ,
                title2 ="Make your backup of Documents & ID Card"){

                val intent=Intent(context,UploadFileActivity::class.java)
                context.startActivity(intent)
            }
           MyWidgetsFunctions.RecentFilesSection()




        }
    }
}