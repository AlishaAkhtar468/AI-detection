package com.resconapss.documents.scanner.pdfgenerator.dialogs

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CustomFolderDetailsActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions.Companion.generalbutton
import com.resconapss.documents.scanner.pdfgenerator.utills.Config


@Composable
fun SuccessfullDeleteDialog(showDialog: Boolean, onDismiss: () -> Unit,context:Activity) {
    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // Optional, to dim the background
                .wrapContentSize(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mytick), // Replace with your drawable resource ID
                    contentDescription = null,
                    modifier = Modifier.size(58.dp)
                )
                Text(
                    text = "Successfully Deleted",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Your folder is successfully Deleted",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)) {
                        generalbutton(title = "Done") {
                            onDismiss()
                        }
                    }
                    }

                }
            }
        }
    }



