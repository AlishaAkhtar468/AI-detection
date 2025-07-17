package com.resconapss.documents.scanner.pdfgenerator.adapters

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.images
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.images1

/*@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerWithImages(bitmap: Bitmap?) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        count = images1.size + if (bitmap != null) 1 else 0, // Add extra page for the bitmap
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (page < images1.size) {
                // Display regular images
                Image(
                    painter = painterResource(id = images1[page]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else if (bitmap != null) {
                // Display the passed bitmap
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Bitmap Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Display the current page number
            Button(
                onClick = {},
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Page: ${pagerState.currentPage + 1}",
                    color = Color.White,
                )
            }*/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerWithImages(bitmap: Bitmap?) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val totalPages = images1.size + if (bitmap != null) 1 else 0

    HorizontalPager(
        state = pagerState,
        count = totalPages,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (page == 0 && bitmap != null) {
                // Display the bitmap on the first page
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Bitmap Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Display the drawable images for subsequent pages
                val imageIndex = if (bitmap != null) page - 1 else page
                if (imageIndex in images1.indices) {
                    Image(
                        painter = painterResource(id = images1[imageIndex]),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Display the current page number
            Button(
                onClick = {},
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Page: ${pagerState.currentPage + 1}",
                    color = Color.White,
                )
            }

           /* Text(
                text = "Page ${pagerState.currentPage + 1}",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 16.dp)
            )*/
        }
    }
}