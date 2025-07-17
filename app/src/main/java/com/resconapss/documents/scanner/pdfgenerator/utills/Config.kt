package com.resconapss.documents.scanner.pdfgenerator.utills

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.resconapss.documents.scanner.pdfgenerator.R
import java.io.ByteArrayOutputStream

class Config {
    companion object
    {
        val popinbold = FontFamily(
        Font(R.font.popinboldsate, FontWeight.Bold)
    )
        val popinreguler = FontFamily(
            Font(R.font.popinregulersate, FontWeight.Normal)
        )

        @Composable
        fun gernal_veriables() {
            var showSuccessBox by remember { mutableStateOf(false) }
        }

        lateinit var mycontext: Activity
        var cameraPermissionGranted:Boolean=false
         var bitmap: Bitmap?=null
        var pagemode=0
        var gallerycamdecider=0
        lateinit var openGalleryLauncher: ActivityResultLauncher<String>

        // view pager images list
        val images = listOf(
            R.drawable.singledoc,
            R.drawable.doubledoc,
            R.drawable.singledoc,
            R.drawable.doubledoc

        )

        val images1 = listOf(R.drawable.singledoc
        )

        //Convert to bytearray
        fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
            return stream.toByteArray()
        }

        //rotate bitmap to 90 degree
        fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
            val matrix = Matrix().apply { postRotate(degrees) }
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }

        //Flipleft to bitmap
        fun flipBitmapHorizontally(bitmap: Bitmap): Bitmap {
            val matrix = Matrix().apply { preScale(-1f, 1f) }
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
    }
}