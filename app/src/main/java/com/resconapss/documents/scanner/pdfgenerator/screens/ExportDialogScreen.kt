package com.resconapss.documents.scanner.pdfgenerator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportDialogScreen() {
    val sheetState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(
                        Color.White, shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
               Column(modifier = Modifier.fillMaxSize()) {

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))
                    {
                        Text(text = stringResource(id = R.string.savein),
                            textAlign = TextAlign.Center,
                            fontFamily = popinreguler,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                        )
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                      )
                    {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                            )
                            {
                                MyWidgetsFunctions.Exporttopdf{

                                }
                            }
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)

                            )
                            {
                                MyWidgetsFunctions.SavetoGallery {

                                }
                            }

                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        )
                    {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .weight(1.3f)
                            )
                            {
                                MyWidgetsFunctions.SaveinClouds{

                                }
                            }
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.7f)

                            )
                            {
                                MyWidgetsFunctions.gotoprimium {

                                }
                            }

                        }
                    }

                }

            }
        },
        sheetPeekHeight = 0.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    sheetState.bottomSheetState.expand()
                }
            }) {
                Text(text = "Show Export Dialog")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExportDialogScreen() {
    ExportDialogScreen()
}
