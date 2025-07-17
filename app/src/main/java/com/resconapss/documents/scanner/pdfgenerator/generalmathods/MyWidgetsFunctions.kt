package com.resconapss.documents.scanner.pdfgenerator.generalmathods

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.DashPathEffect
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraScanActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraView
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CloudBackupHomeActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.DeleteCustomFolderActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.IDCardScanActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.MoreToolsActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.SettingActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.UploadingFileActivity
import com.resconapss.documents.scanner.pdfgenerator.models.document_item
import com.resconapss.documents.scanner.pdfgenerator.screens.CustomFolderDetailsScreen
import com.resconapss.documents.scanner.pdfgenerator.screens.IDCardScannScreen
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinbold
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinreguler
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.cameraPermissionGranted
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext
import kotlinx.coroutines.launch

class MyWidgetsFunctions {

    companion object{

       //my custom toolbar
        @Composable
        fun my_custom_toolbar(title: String, context: Activity)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.backblackarrow), // Replace with your icon resource
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(25.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                        .clickable {
                            // Handle click event and navigate to next activity
                            context.finish()
                        }
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    text = title,
                    fontSize = 20.sp,
                    fontFamily = popinbold,
                    color = colorResource(id = R.color.black)
                )

            }

        }
        //toolbar with done or next button
        @Composable
        fun  my_customtoolbarwith_button(title: String,button_title:String,context: Activity,onClick: () -> Unit)
        {
            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(5.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                    )
                    {
                        Image(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                                .padding(15.dp)
                                .clickable { onClick() },
                            painter = painterResource(id = R.drawable.backblackarrow),  // Replace with your drawable resource ID
                            contentDescription = "Profile"
                        )

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1.7f)
                    )
                    {
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center)
                                .padding(start = 10.dp),
                            text = title,
                            fontSize = 20.sp,
                            fontFamily = popinbold,
                            color = colorResource(id = R.color.black)
                        )
                    }
                    Box(

                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable { onClick() }
                            .weight(0.8f)
                            .padding(10.dp)
                            .background(
                                colorResource(R.color.neela),
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    {
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            text = button_title,
                            fontSize = 12.sp,
                            fontFamily = popinbold,
                            color = colorResource(id = R.color.white)
                        )
                    }

                }
            }
        }

       //general button
       @Composable
       fun generalbutton(title: String,onClick: () -> Unit) {
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(8.dp)
                   .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                   .clickable(onClick = onClick),
               contentAlignment = Alignment.Center
           ) {
               Text(
                   text = title,
                   color = Color.White,
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Normal
               )
           }
       }

        //general button small
        @Composable
        fun generalbuttonsmall(title: String,onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(color = Color(0xFF0F1724), shape = RoundedCornerShape(8.dp))
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }


        //Createa folder button
        @Composable
        fun createafolder(onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.createfoldericon), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.createfolder),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }

        // upload from gallery

        @Composable
        fun UploadFromGalleryBox(onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.icgallery), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.upload_from_gallery),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                    )
                }
            }
        }

        //export to pdf files
        @Composable
        fun Exporttopdf(onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.pdfs), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.exporttopdf),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }

        //save to gallery
        @Composable
        fun SavetoGallery(onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.imagesave), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.saveingallery),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }
        //save in clould
        @Composable
        fun SaveinClouds(onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = Color(0xFF4285F4),
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 0.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.cloud), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.saveincloud),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }
        //go to primium button
        @Composable
        fun gotoprimium(onClick: () -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = Color(0xFFFFC107),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 8.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.primium), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.primium),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }
        //export document
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun ExportDpcument(onClick: () -> Unit) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color(0xFF4285F4), shape = RoundedCornerShape(8.dp))
                    .clickable {


                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.exportdocuments),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }
      //upgrade to premium
        @Composable
        fun upgradetoprimium(onClick: () -> Unit) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
                    .background(color = Color(0xff0b172c), shape = RoundedCornerShape(18.dp))
                    .clickable {


                    }
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.upgradetoprimium),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = popinreguler,
                        fontWeight = FontWeight.Normal,

                        )
                }
            }
        }

        //banner add lay
        @Composable
        fun bannerlay() {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 10.dp)
                .background(Color.White)
            )

        }

        //Home top app bar
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun TopBar(context: Activity) {

            TopAppBar(
                title = {
                    Column(modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)) {
                        Text(
                            "All Document Scanner",
                            fontWeight = FontWeight.Bold,
                            fontFamily = popinbold
                        )
                        Text("ID Card Scanner & Documents Scanner", fontSize = 12.sp, fontFamily = popinreguler, color = colorResource(
                            id = R.color.lightblue
                        )
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {

                        val intent=Intent(context,MoreToolsActivity::class.java)
                        context.startActivity(intent)
                    }) {

                        Image(
                            painter = painterResource(id = R.drawable.tools),  // Replace with your drawable resource ID
                            contentDescription = "Profile"
                        )
                    }
                    IconButton(onClick = {
                        val intent=Intent(context,SettingActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.mysettings),  // Replace with your drawable resource ID
                            contentDescription = "Profile"
                        )
                    }
                },

                )
        }

        //primium offer screen
        @Composable
        fun PremiumOfferSection( context: Activity) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp)
                    .background(colorResource(id = R.color.halfgray)),

                )
            {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .background(Color.White)
                )
                {
                    Column(modifier = Modifier.fillMaxSize()) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp)

                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(0.8f)
                                        .fillMaxWidth()

                                )
                                {
                                    Text("All Document Cloud Backup (PREMIUM)",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = popinbold,
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .padding(top = 1.dp, start = 10.dp)
                                            .align(Alignment.Center)

                                    )
                                }


                                Box(
                                    modifier = Modifier
                                        .weight(1.2f)
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight()
                                                .padding(2.dp)
                                                .background(
                                                    color = colorResource(id = R.color.halfgray), // Same as #2a3c42
                                                    shape = RoundedCornerShape(
                                                        topStart = 10.dp,
                                                        topEnd = 0.dp,
                                                        bottomEnd = 0.dp,
                                                        bottomStart = 10.dp
                                                    )
                                                )
                                        )
                                        {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(8.dp)
                                            ){
                                                Box(
                                                    modifier = Modifier
                                                        .size(60.dp).clickable {
                                                            val intent=Intent(context, CloudBackupHomeActivity::class.java)
                                                            context.startActivity(intent)
                                                        }
                                                        .background(Color.White, RoundedCornerShape(8.dp)),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.cloudicon), // Replace with your icon resource
                                                        contentDescription = null,
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(

                                                    text = "Make your all documents backup",
                                                    color = Color(0xFF2E2E2E), // Dark text color
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Medium,

                                                    )
                                            }

                                        }

                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight()
                                                .padding(2.dp)
                                                .background(
                                                    color = colorResource(id = R.color.halfgray), // Same as #2a3c42
                                                    shape = RoundedCornerShape(
                                                        topStart = 0.dp,
                                                        topEnd = 10.dp,
                                                        bottomEnd = 10.dp,
                                                        bottomStart = 0.dp
                                                    )
                                                )
                                        )
                                        {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(8.dp)
                                            ){
                                                Box(
                                                    modifier = Modifier
                                                        .size(60.dp)
                                                        .background(
                                                            Color.White,
                                                            RoundedCornerShape(8.dp)
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.cloudicon1), // Replace with your icon resource
                                                        contentDescription = null,
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(

                                                    text = "Share your Files  by Cloud",
                                                    color = Color(0xFF2E2E2E), // Dark text color
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Medium,

                                                    )
                                            }
                                        }
                                    }
                                }



                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()

                                )
                                {

                                    Button(onClick = {}
                                        ,modifier= Modifier
                                            .fillMaxSize()
                                            .padding(3.dp),
                                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black)), // Change the button color here
                                        shape = RoundedCornerShape(12.dp)


                                    ) {


                                        Text(
                                            text = "Annual 15$ Purchase Now",
                                            color = Color.White,  // Adjust text color as needed
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = popinbold
                                        )
                                    }

                                }
                            }
                        }



                    }
                }
            }


        }

        //scanner options
        @Composable
        fun ScannerOptions(context:Activity) {

            val requestPermissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.cameraPermissionGranted = isGranted
                if (isGranted) {

                  // context.startActivity(Intent(context, CameraScanActivity::class.java))

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp)

            ) {

                Box(


                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(5.dp)
                        .background(colorResource(id = R.color.sky))
                        .clickable {

                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    android.Manifest.permission.CAMERA
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                            } else {
                                cameraPermissionGranted = true
                                context.startActivity(
                                    Intent(
                                        context,
                                        CameraView::class.java
                                    )
                                )


                            }
                            // Handle click event and navigate to next activity
                        },


                )
                {
                    Column(modifier = Modifier.fillMaxSize()) {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.docicon), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterHorizontally)
                                .wrapContentSize()
                        )

                        {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Documents Scanner",
                                    color = colorResource(id = R.color.dimblue),  // Adjust text color as needed
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = popinbold,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Scan Your Documents",
                                    color = colorResource(id = R.color.black),  // Adjust text color as needed
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = popinreguler,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }





                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(5.dp)
                        .background(colorResource(id = R.color.sky))
                        .clickable {

                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    android.Manifest.permission.CAMERA
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                            } else {
                                cameraPermissionGranted = true
                                context.startActivity(
                                    Intent(
                                        context,
                                        IDCardScanActivity()::class.java
                                    )
                                )


                            }
                            // Handle click event and navigate to next activity
                        }

                )
                {
                    Column(modifier = Modifier.fillMaxSize()) {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.myid), // Replace with your icon resource
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterHorizontally)
                                .wrapContentSize()
                        )

                        {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "ID Card Scanner",
                                    color = colorResource(id = R.color.dimblue),  // Adjust text color as needed
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = popinbold,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Scan Your id Card",
                                    color = colorResource(id = R.color.black),  // Adjust text color as needed
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = popinreguler,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }





                    }
                }



            }
        }
        @Composable
        //recent files
        fun RecentFilesSection() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.liqua))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Recent Files", fontWeight = FontWeight.Bold,modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically))
                    TextButton(onClick = { /* Handle click */ },
                        Modifier
                            .fillMaxHeight()
                            .width(90.dp)
                            .padding(5.dp)) {
                        Text("See All")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(5) { index ->
                        RecentFileItem(
                            title = "Important Study Document000012",
                            date = "Date:10/20/2023",
                            time = "Time:10:40:00PM"
                        )
                        if (index < 4) {
                            Divider()
                        }
                    }
                }
            }
        }
      //recent files1
        @Composable
        fun RecentFilesSection1() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.liqua))
            ) {

                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(5) { index ->
                        RecentFileItem(
                            title = "Important Study Document000012",
                            date = "Date:10/20/2023",
                            time = "Time:10:40:00PM"
                        )
                        if (index < 4) {
                            Divider()
                        }
                    }
                }
            }
        }

        //recent files item
        @Composable
        fun RecentFileItem(title: String, date: String, time: String) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(
                        Color.White, RoundedCornerShape(
                            5
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(5.dp)
                        .background(colorResource(id = R.color.sky))
                ) {
                    // Placeholder for document image
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.padding(top=7.dp)) {
                    Text(title, fontWeight = FontWeight.Bold)
                    Text("$date  $time", fontSize = 12.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle click */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
            }
        }
        //dov view
        @Composable
        fun  doc_view( title: String,imgid:Int, onClick: () -> Unit)
        {

            Column(

                modifier = Modifier
                    .width(70.dp)
                    .height(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(5.dp))
                    .padding(5.dp)
                    .clickable(
                        onClick = onClick
                    )
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f))
                {

                    Image(
                        painter = painterResource(id = imgid), // Replace with your icon resource
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                    )
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f)
                    .background(color = colorResource(id = R.color.naveyblue)))
                {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = title,
                        color = colorResource(id = R.color.white),  // Adjust text color as needed
                        fontWeight = FontWeight.Normal,
                        fontFamily = popinreguler,
                        fontSize = 7.sp,
                        textAlign = TextAlign.Center
                    )
                }


            }

        }
        @Composable
        fun side_change_text(title: String)
        {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = title,
                color = colorResource(id = R.color.white),  // Adjust text color as needed
                fontWeight = FontWeight.Normal,
                fontFamily = popinreguler,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

        }
        @Composable
        fun ShowDocument2(myBitmap: Bitmap) {
            val imageBitmap = myBitmap.asImageBitmap()

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
        @Composable
        fun show_filters()
        {
            LazyRow(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                items(10) { index ->
                    Image(
                        painter = painterResource(id = R.drawable.mydocthumb),
                        contentDescription = "Image $index",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        //setflash light on off
        fun setFlashLight(context: Context, isFlashOn: Boolean) {
            val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val cameraId = cameraManager.cameraIdList[0] // Assuming the device has at least one camera
            try {
                cameraManager.setTorchMode(cameraId, isFlashOn)
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
        @Composable
        fun DocumentList(documents: List<document_item>) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        colorResource(id = R.color.liqua),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .padding(16.dp)
            ) {
                items(documents) { document ->
                    DocumentItem(document)
                }
            }
        }

        @Composable
        fun DocumentItem(document: document_item) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            )  {
                Image(
                    painter = painterResource(id = R.drawable.pagethumb), // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(4f)
                ) {
                    Text(
                        text = document.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Date: ${document.date}   Time: ${document.time}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Check",
                    tint = Color.Blue,
                    modifier = Modifier
                        .size(24.dp)
                        .weight(1f)
                )
            }
        }
        // create folder
        @Composable
        fun create_folderlay()
        {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(20.dp)
                        .background(
                            colorResource(id = R.color.neela),
                            shape = RoundedCornerShape(10.dp)
                        ))
                    {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(1.dp)
                            .background(
                                colorResource(id = R.color.sky),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                                Image(painter = painterResource(id = R.drawable.addsum),
                                    contentDescription ="create",
                                    modifier = Modifier.clickable {

                                    }

                                )
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp))
                            Text(
                               
                                text = stringResource(id = R.string.createfolder),
                                color = colorResource(id = R.color.black),  // Adjust text color as needed
                                fontWeight = FontWeight.Normal,
                                fontFamily = popinreguler,
                                fontSize = 17.sp,
                                textAlign = TextAlign.Center
                            )


                            
                        }

                    }

        }
        //Delete Document list
        @Composable
        fun DeleteDocumentList(documents: List<document_item>,context: Activity) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        colorResource(id = R.color.liqua),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .padding(16.dp)
            ) {
                items(documents) { document ->
                    DeleteDocumentItem(document, context =context )
                }
            }
        }
        // delete document item
        @Composable
        fun DeleteDocumentItem(document: document_item,context: Activity) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.docthumb), // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(4f)
                ) {
                    Text(
                        text = document.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Date: ${document.date}   Time: ${document.time}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                }

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.deletebtn),
                    contentDescription = "delete",
                    modifier = Modifier
                        .size(34.dp)
                        .weight(1f)
                        .clickable {
                            val intent = Intent(context, DeleteCustomFolderActivity::class.java)
                            context.startActivity(intent)
                        }
                )
            }
        }

        //Delete Document list
        @Composable
        fun CustomDeleteDocumentList(documents: List<document_item>,context: Activity,dshow:Boolean) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        colorResource(id = R.color.liqua),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .padding(16.dp)
            ) {
                items(documents) { document ->
                    CustomDeleteDocumentItem(document, context =context ,dshow)
                }
            }
        }
        // delete document item
        @Composable
        fun CustomDeleteDocumentItem(document: document_item,context: Activity,showdialog:Boolean) {
            var myshow by remember { mutableStateOf(false) }
            myshow=showdialog

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.docthumb), // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(4f)
                ) {
                    Text(
                        text = document.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Date: ${document.date}   Time: ${document.time}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)) {

                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(2.dp))
                        {
                            MyWidgetsFunctions.generalbuttonsmall(title = stringResource(id = R.string.edit)) {
                                
                            }
                        }
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(2.dp))
                        {
                            MyWidgetsFunctions.generalbuttonsmall(title = stringResource(id = R.string.share)) {

                            }
                        }


                    }

                }

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.deletebtn),
                    contentDescription = "delete",
                    modifier = Modifier
                        .size(34.dp)
                        .weight(1f)
                        .clickable {


                            Toast
                                .makeText(context, "$myshow", Toast.LENGTH_SHORT)
                                .show()

                        }
                )
            }
        }




    }




}