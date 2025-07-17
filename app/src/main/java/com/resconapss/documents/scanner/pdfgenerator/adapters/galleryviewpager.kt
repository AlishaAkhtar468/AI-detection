package com.resconapss.documents.scanner.pdfgenerator.adapters

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun galleryviewpager(context:Activity) {
    val imageUris = context.intent.getParcelableArrayListExtra<Uri>("imageUris")
    if (imageUris != null) {
        val pagerState = rememberPagerState()

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ) {
            HorizontalPager(
                count = imageUris.size,

                modifier = Modifier.fillMaxSize().padding(10.dp)
            ) { page ->
                AsyncImage(
                    model = imageUris[page],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
