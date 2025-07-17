package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.models.ImageItem
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler

@Composable
fun CloudBackup (context: Activity){

    val imageItems = listOf(
        ImageItem(imageRes = R.drawable.mydocthumb, name = "Doc 1"),
        ImageItem(imageRes = R.drawable.mydocthumb, name = "Doc 2"),
        ImageItem(imageRes = R.drawable.mydocthumb, name = "Doc 3"),
        ImageItem(imageRes = R.drawable.mydocthumb, name = "Doc 4")
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f))
            {
                MyWidgetsFunctions.my_custom_toolbar(title = stringResource(id = R.string.cloudbackup), context = context)

            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f))
            {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.TopStart)
                        .padding(start = 10.dp,top =10.dp),
                    text = "Date:10/12/2024",
                    fontSize = 13.sp,
                    fontFamily = popinreguler,
                    color = colorResource(id = R.color.black)
                )

            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(3.4f))
            {
                ImageGrid(imageItems = imageItems)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f))
            {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.TopStart)
                        .padding(start = 10.dp, top = 10.dp),
                    text = "Date:10/12/2024",
                    fontSize = 13.sp,
                    fontFamily = popinreguler,
                    color = colorResource(id = R.color.black)
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f))
            {
                MyWidgetsFunctions.UploadFromGalleryBox {

                }
            }

        }
    }
}
@Composable
fun ImageGrid(imageItems: List<ImageItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(imageItems) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(228.dp)
                        .padding(8.dp)
                )
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}
