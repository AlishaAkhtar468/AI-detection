package com.resconapss.documents.scanner.pdfgenerator.activitvities


import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


class ShowImagePagerActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the list of image URIs from the intent
        val imageUris = intent.getParcelableArrayListExtra<Uri>("imageUris")

        setContent {
            MaterialTheme {
                if (imageUris != null) {
                    val pagerState = rememberPagerState()

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                    ) {
                        HorizontalPager(
                            count = imageUris.size,

                            modifier = Modifier.fillMaxSize()
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
        }
    }
}